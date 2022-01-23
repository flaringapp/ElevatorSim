package com.flaringapp.elevator_core.person_factory

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.person.Person

class DefaultPersonFactory(
    private val idFactory: PersonIdFactory,
    private val nameFactory: PersonNameFactory,
    private val weightFactory: PersonWeightFactory,
    private val tripFactory: PersonTripFactory,
) : PersonFactory {

    override fun createPerson(building: Building): Person {
        return Person(
            id = idFactory.createId(),
            name = nameFactory.createName(),
            weight = weightFactory.createWeight(),
            trip = tripFactory.createTrip(building),
        )
    }
}