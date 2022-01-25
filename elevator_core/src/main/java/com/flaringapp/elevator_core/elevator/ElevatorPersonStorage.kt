package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.common.Weight
import com.flaringapp.elevator_core.person.Person
import kotlinx.coroutines.flow.SharedFlow

interface ElevatorPersonStorage {

    val totalWeight: Weight

    val changedFlow: SharedFlow<Unit>

    fun addPerson(person: Person)

    fun removePerson(person: Person)

    operator fun plusAssign(person: Person) {
        addPerson(person)
    }

    operator fun minusAssign(person: Person) {
        removePerson(person)
    }

}