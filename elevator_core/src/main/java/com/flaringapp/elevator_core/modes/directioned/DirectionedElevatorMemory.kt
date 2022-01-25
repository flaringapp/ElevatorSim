package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.floor.FloorNumber
import java.util.*

interface DirectionedElevatorMemory {

    val calledUpFloors: NavigableSet<FloorNumber>
    val calledDownFloors: NavigableSet<FloorNumber>
    val targetFloors: NavigableSet<FloorNumber>

    fun hasAnyCalls(): Boolean

}

class DirectionedElevatorMemoryImpl : DirectionedElevatorMemory {

    override val calledUpFloors = TreeSet<FloorNumber>()
    override val calledDownFloors = TreeSet<FloorNumber>()
    override val targetFloors = TreeSet<FloorNumber>()

    override fun hasAnyCalls(): Boolean {
        return calledUpFloors.isNotEmpty() ||
            calledDownFloors.isNotEmpty() ||
            targetFloors.isNotEmpty()
    }
}