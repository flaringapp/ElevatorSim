package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.person.Person

data class Elevator(
    val id: ElevatorId,
    val name: String,
    val specs: ElevatorSpecs,
    val state: ElevatorState,
    val personStorage: ElevatorPersonStorage,
) : ElevatorDoor {

    override fun enter(person: Person) {
        personStorage.addPerson(person)
    }

    override fun exit(person: Person) {
        personStorage.removePerson(person)
    }
}