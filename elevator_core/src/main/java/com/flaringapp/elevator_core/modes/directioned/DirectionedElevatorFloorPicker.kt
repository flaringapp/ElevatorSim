package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.floor.FloorNumber

interface DirectionedElevatorFloorPicker {

    fun pickClosestFloor(
        currentFloor: FloorNumber,
        memory: DirectionedElevatorMemory,
    ): FloorNumber?

}

class DirectionedElevatorHigherFloorPicker : DirectionedElevatorFloorPicker {

    private val comparator = Comparator.naturalOrder<FloorNumber>()

    override fun pickClosestFloor(
        currentFloor: FloorNumber,
        memory: DirectionedElevatorMemory,
    ): FloorNumber? = with(memory) {
        return chooseClosestFloor(
            first = calledUpFloors.higher(currentFloor),
            second = targetFloors.higher(currentFloor),
            comparator = comparator
        )
            ?: calledDownFloors.higher(currentFloor)
            ?: calledDownFloors.lower(currentFloor)
    }
}

class DirectionedElevatorLowerFloorPicker : DirectionedElevatorFloorPicker {

    private val comparator = Comparator.reverseOrder<FloorNumber>()

    override fun pickClosestFloor(
        currentFloor: FloorNumber,
        memory: DirectionedElevatorMemory,
    ): FloorNumber? = with(memory) {
        return chooseClosestFloor(
            first = calledDownFloors.lower(currentFloor),
            second = targetFloors.lower(currentFloor),
            comparator = comparator
        )
            ?: calledUpFloors.lower(currentFloor)
            ?: calledUpFloors.higher(currentFloor)
    }
}

private fun chooseClosestFloor(
    first: FloorNumber?,
    second: FloorNumber?,
    comparator: Comparator<FloorNumber>,
): FloorNumber? {
    return when {
        first == null -> second
        second == null -> first
        else -> {
            if (comparator.compare(first, second) < 0) first
            else second
        }
    }
}
