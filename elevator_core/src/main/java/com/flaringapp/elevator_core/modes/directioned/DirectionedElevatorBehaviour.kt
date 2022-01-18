package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.elevator_behaviour.ElevatorBehaviour
import com.flaringapp.elevator_core.floor.FloorNumber

interface DirectionedElevatorBehaviour : ElevatorBehaviour {

    fun callUpwards(floor: FloorNumber)
    fun callDownwards(floor: FloorNumber)

}