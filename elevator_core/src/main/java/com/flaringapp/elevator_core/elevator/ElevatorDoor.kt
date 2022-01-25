package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.person.Person

interface ElevatorDoor {

    fun enter(person: Person)

    fun exit(person: Person)

}