package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId

interface BuildingQueueInteractor {

    fun enterQueue(floor: FloorNumber, person: Person)

    fun pollPerson(floor: FloorNumber, elevator: Elevator): PersonId

    fun cleanup()

}