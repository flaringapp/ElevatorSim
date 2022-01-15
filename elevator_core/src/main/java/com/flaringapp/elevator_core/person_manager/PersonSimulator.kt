package com.flaringapp.elevator_core.person_manager

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.person.Person

interface PersonSimulator {

    fun simulate(building: Building, person: Person)

}