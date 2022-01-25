package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.common.Direction
import com.flaringapp.elevator_core.common.transformFor
import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.utils.use
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class DirectionedElevatorBehaviourImpl(
    higherFloorPicker: DirectionedElevatorFloorPicker,
    lowerFloorPicker: DirectionedElevatorFloorPicker,
    private val elevator: Elevator,
) : DirectionedElevatorBehaviour,
    DirectionedElevatorMemory by DirectionedElevatorMemoryImpl() {

    private val lock = ReentrantLock()

    private val nextFloorPickerProvider = NextFloorPickerProvider(
        higherFloorPicker = higherFloorPicker,
        lowerFloorPicker = lowerFloorPicker,
    )

    private val directionCalledFloorsProvider = DirectionCalledFloorsProvider(this)

    override val pendingCallFlow = MutableStateFlow(false)

    override fun chooseNextFloor(currentFloor: FloorNumber): FloorNumber? = lock.withLock {
        val nextFloorPicker = nextFloorPickerProvider.transformFor(elevator)
        return nextFloorPicker.pickClosestFloor(currentFloor, this)
    }

    override fun processFloorReached(floor: FloorNumber) = lock.use {
        targetFloors.remove(floor)

        val directionCalledFloors = directionCalledFloorsProvider.transformFor(elevator)
        directionCalledFloors.remove(floor)

        validateIfStillCalled()
    }

    override fun callUpwards(floor: FloorNumber) = lock.use {
        calledUpFloors += floor
        notifyCalled()
    }

    override fun callDownwards(floor: FloorNumber) = lock.use {
        calledDownFloors += floor
        notifyCalled()
    }

    override fun requestTargetFloor(floor: FloorNumber) = lock.use {
        targetFloors += floor
        notifyCalled()
    }

    private fun validateIfStillCalled() {
        if (hasAnyCalls()) return
        pendingCallFlow.compareAndSet(expect = true, update = false)
    }

    private fun notifyCalled() {
        pendingCallFlow.compareAndSet(expect = false, update = true)
    }

    private class NextFloorPickerProvider(
        private val higherFloorPicker: DirectionedElevatorFloorPicker,
        private val lowerFloorPicker: DirectionedElevatorFloorPicker,
    ) : Direction.Transformer<DirectionedElevatorFloorPicker> {

        override fun upwards() = higherFloorPicker

        override fun downwards() = lowerFloorPicker
    }

    private class DirectionCalledFloorsProvider(
        private val memory: DirectionedElevatorMemory
    ) : Direction.Transformer<NavigableSet<FloorNumber>> {

        override fun upwards(): NavigableSet<FloorNumber> = memory.calledUpFloors

        override fun downwards(): NavigableSet<FloorNumber> = memory.calledDownFloors
    }
}