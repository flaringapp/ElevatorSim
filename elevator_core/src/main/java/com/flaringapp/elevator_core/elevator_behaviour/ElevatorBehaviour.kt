package com.flaringapp.elevator_core.elevator_behaviour

import com.flaringapp.elevator_core.elevator_panel.ElevatorPanel
import com.flaringapp.elevator_core.floor.FloorNumber

interface ElevatorBehaviour : ElevatorPanel {

    fun chooseNextFloor(currentFloor: FloorNumber): FloorNumber?

    fun processFloorReached(floor: FloorNumber)

}