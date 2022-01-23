package com.flaringapp.elevator_core.person_factory

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.person.Person

interface PersonFactory {

    fun createPerson(building: Building): Person

}