package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.common.Direction
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person_manager.PersonElevatorCaller

class DirectionedPersonElevatorCaller(
    private val panel : DirectionedElevatorPanel
) : PersonElevatorCaller {

    override fun callOutside(initialFloor: FloorNumber, targetFloor: FloorNumber) {
        val compare = initialFloor compareTo targetFloor
        val direction = when {
            compare > 0 -> Direction.Downwards
            compare < 0 -> Direction.Upwards
            else -> error("Person tries to call elevator to the same floor")
        }
        panel.call(initialFloor, direction)
    }

    override fun callInside(targetFloor: FloorNumber) {
        panel.requestFloor(targetFloor)
    }
}