package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.common.Direction
import com.flaringapp.elevator_core.common.transformFor
import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.floor.FloorNumber
import java.util.*

// TODO synchronize
class DirectionedElevatorBehaviourImpl(
    higherFloorPicker: DirectionedElevatorFloorPicker,
    lowerFloorPicker: DirectionedElevatorFloorPicker,
    private val elevator: Elevator,
) : DirectionedElevatorBehaviour, DirectionedElevatorMemory {

    override val calledUpFloors = TreeSet<FloorNumber>()
    override val calledDownFloors = TreeSet<FloorNumber>()
    override val targetFloors = TreeSet<FloorNumber>()

    private val nextFloorPickerProvider = NextFloorPickerProvider(
        higherFloorPicker = higherFloorPicker,
        lowerFloorPicker = lowerFloorPicker,
    )

    private val directionCalledFloorsProvider = DirectionCalledFloorsProvider(this)

    override fun chooseNextFloor(currentFloor: FloorNumber): FloorNumber? {
        val nextFloorPicker = nextFloorPickerProvider.transformFor(elevator)
        return nextFloorPicker.pickClosestFloor(currentFloor, this)
    }

    override fun processFloorReached(floor: FloorNumber) {
        targetFloors.remove(floor)

        val directionCalledFloors = directionCalledFloorsProvider.transformFor(elevator)
        directionCalledFloors.remove(floor)
    }

    override fun callUpwards(floor: FloorNumber) {
        calledUpFloors += floor
    }

    override fun callDownwards(floor: FloorNumber) {
        calledDownFloors += floor
    }

    override fun requestTargetFloor(floor: FloorNumber) {
        targetFloors += floor
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