package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId
import com.flaringapp.elevator_core.queue.FloorQueue
import com.flaringapp.elevator_core.queue.FloorQueueProvider

class BuildingQueueInteractorImpl(
    private val building: Building,
    private val floorQueueProvider: FloorQueueProvider,
) : BuildingQueueInteractor {

    private val queues: MutableMap<FloorNumber, FloorQueue> = HashMap()

    override fun enterQueue(floor: FloorNumber, person: Person) {
        val queue = resolveQueue(floor)
        queue.enterQueue(person)
    }

    override fun pollPerson(floor: FloorNumber, elevator: Elevator): PersonId {
        val queue = requireQueue(floor)
        return queue.pollPerson(elevator)
    }

    override fun cleanup() {
        queues.forEach { (_, queue) -> queue.cleanup() }
    }

    private fun resolveQueue(floor: FloorNumber): FloorQueue {
        return queues[floor] ?: createQueue(floor)
    }

    private fun createQueue(floor: FloorNumber): FloorQueue {
        return floorQueueProvider.createQueue(building).also {
            queues[floor] = it
        }
    }

    private fun requireQueue(floor: FloorNumber): FloorQueue {
        return queues[floor] ?: missingQueueError(floor)
    }

    private fun missingQueueError(floor: FloorNumber): Nothing {
        error("Cannot find queue for floor $floor")
    }
}