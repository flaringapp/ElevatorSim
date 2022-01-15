package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.floor.Floor

class Building(
    val name: String,
    val floors: List<Floor>,
    val elevators: List<Elevator>,
) {

    fun firstElevator(): Elevator = elevators.first()

}