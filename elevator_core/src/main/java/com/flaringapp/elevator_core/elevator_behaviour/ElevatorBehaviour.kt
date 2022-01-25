package com.flaringapp.elevator_core.elevator_behaviour

import com.flaringapp.elevator_core.elevator_panel.ElevatorPanel
import com.flaringapp.elevator_core.floor.FloorNumber
import kotlinx.coroutines.flow.StateFlow

interface ElevatorBehaviour : ElevatorPanel {

    val pendingCallFlow: StateFlow<Boolean>

    fun chooseNextFloor(currentFloor: FloorNumber): FloorNumber?

    fun processFloorReached(floor: FloorNumber)

}