package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.Floor

class Building(
    val name: String,
    val floors: List<Floor>,
    val elevators: MutableMap<ElevatorId, Elevator> = HashMap(),
) {

    init {
        require(floors.size > 1) { "There should be at least 2 floors in the building!" }
        require(elevators.isNotEmpty()) { "There should be at least 1 elevator in the building!" }
    }

    fun firstElevator(): Elevator = elevators.values.first()

    fun requireElevator(elevatorId: ElevatorId) = elevators[elevatorId]
        ?: error("Requiring unknown elevator $elevatorId")

}