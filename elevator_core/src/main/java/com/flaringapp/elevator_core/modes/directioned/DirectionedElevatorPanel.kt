package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.elevator_panel.ElevatorPanel
import com.flaringapp.elevator_core.floor.FloorNumber

interface DirectionedElevatorPanel : ElevatorPanel {

    fun callUpwards(floor: FloorNumber)
    fun callDownwards(floor: FloorNumber)

}