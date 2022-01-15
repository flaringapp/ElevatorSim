package com.flaringapp.elevator_core.person_manager

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.building.BuildingQueueInteractor
import com.flaringapp.elevator_core.person.Person

class PersonSimulatorImpl(
    private val buildingQueueInteractor: BuildingQueueInteractor,
) : PersonSimulator {

    override fun simulate(building: Building, person: Person) {
        val initialFloorNumber = person.trip.initialFloor

        buildingQueueInteractor.enterQueue(
            floor = initialFloorNumber,
            person = person,
        )
    }
}