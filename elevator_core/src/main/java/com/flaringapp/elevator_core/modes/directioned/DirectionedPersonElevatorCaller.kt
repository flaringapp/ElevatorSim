package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person_manager.PersonElevatorCaller

class DirectionedPersonElevatorCaller(
    private val panel : DirectionedElevatorPanel
) : PersonElevatorCaller {

    override fun callOutside(initialFloor: FloorNumber, targetFloor: FloorNumber) {
        val compare = initialFloor compareTo targetFloor
        when {
            compare > 0 -> panel.callDownwards(initialFloor)
            compare < 0 -> panel.callUpwards(initialFloor)
            else -> error("Person tries to call elevator to the same floor")
        }
    }

    override fun callInside(targetFloor: FloorNumber) {
        panel.requestTargetFloor(targetFloor)
    }
}