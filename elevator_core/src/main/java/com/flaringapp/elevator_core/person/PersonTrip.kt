package com.flaringapp.elevator_core.person

import com.flaringapp.elevator_core.floor.FloorNumber

data class PersonTrip(
    val initialFloor: FloorNumber,
    val targetFloor: FloorNumber,
)