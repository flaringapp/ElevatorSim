package com.flaringapp.elevator_core.person_factory

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.person.PersonTrip
import kotlin.random.Random

interface PersonTripFactory {

    fun createTrip(building: Building): PersonTrip

}

class RandomPersonTripFactory : PersonTripFactory {

    override fun createTrip(building: Building): PersonTrip {
        val floors = building.floors
        val initialFloorIndex = Random.nextInt(floors.size)

        val isTargetFloorHigher = Random.nextBoolean()

        val targetFloorIndex =
            if (isTargetFloorHigher && initialFloorIndex != floors.lastIndex) {
                Random.nextInt(initialFloorIndex + 1, floors.lastIndex)
            } else {
                Random.nextInt(initialFloorIndex)
            }

        return PersonTrip(
            initialFloor = floors[initialFloorIndex].number,
            targetFloor = floors[targetFloorIndex].number
        )
    }
}