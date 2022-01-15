package com.flaringapp.elevator_core.person_manager

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.building.BuildingQueueInteractor
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.queue_picker.ElevatorQueuePicker

class PersonSimulatorImpl(
    private val elevatorQueuePicker: ElevatorQueuePicker,
    private val buildingQueueInteractor: BuildingQueueInteractor,
) : PersonSimulator {

    override fun simulate(building: Building, person: Person) {
        val initialFloorNumber = person.trip.initialFloor
        val queueElevatorId = elevatorQueuePicker.pick(initialFloorNumber)

        buildingQueueInteractor.enterQueue(
            floor = initialFloorNumber,
            elevator = queueElevatorId,
            person = person.id,
        )
    }
}