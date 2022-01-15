package com.flaringapp.elevator_core.queue

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId

interface FloorQueue {

    fun enterQueue(person: Person)

    fun pollPerson(elevator: Elevator): PersonId

    fun isEmpty(): Boolean

    fun cleanup()

}