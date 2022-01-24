package com.flaringapp.elevator_core.queue

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId

interface FloorQueue {

    fun enterQueue(person: Person): ElevatorId

    fun pollPerson(elevator: ElevatorId): PersonId
    fun peekPerson(elevator: ElevatorId): PersonId

    fun isEmpty(): Boolean

    fun cleanup()

}