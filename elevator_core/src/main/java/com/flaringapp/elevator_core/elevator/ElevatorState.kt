package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.common.Direction
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.Person

data class ElevatorState(
    var movementDirection: Direction,
    var currentFloor: FloorNumber,
    var isOpened: Boolean,
)