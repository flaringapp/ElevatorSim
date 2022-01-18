package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.Person

data class ElevatorState(
    var currentFloor: FloorNumber,
    var passengers: MutableList<Person>,
    val isOpened: Boolean,
)