package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.PersonId

interface BuildingQueueInteractor {

    fun findElevatorWithSmallestQueue(floor: FloorNumber): ElevatorId

    fun enterQueue(floor: FloorNumber, elevator: ElevatorId, person: PersonId)

    fun exitQueue(floor: FloorNumber, elevator: ElevatorId): PersonId

    fun cleanup()

}