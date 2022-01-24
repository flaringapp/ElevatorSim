package com.flaringapp.elevator_core.elevator_panel

import com.flaringapp.elevator_core.floor.FloorNumber

interface ElevatorPanel {

    fun requestFloor(floor: FloorNumber)

}