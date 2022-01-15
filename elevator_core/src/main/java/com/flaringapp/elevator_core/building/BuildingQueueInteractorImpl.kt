package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.PersonId

class BuildingQueueInteractorImpl(
    private val building: Building,
) : BuildingQueueInteractor {

    private val queues: BuildingFloorQueues = BuildingFloorQueues()

    override fun findElevatorWithSmallestQueue(floor: FloorNumber): ElevatorId {
        return queues.findElevatorWithSmallestQueue(floor) ?: building.firstElevator().id
    }

    override fun enterQueue(floor: FloorNumber, elevator: ElevatorId, person: PersonId) {
        return queues.enter(
            floor = floor,
            elevator = elevator,
            person = person,
        )
    }

    override fun exitQueue(floor: FloorNumber, elevator: ElevatorId): PersonId {
        return queues.exit(
            floor = floor,
            elevator = elevator,
        )
    }

    override fun cleanup() {
        queues.cleanup()
    }
}