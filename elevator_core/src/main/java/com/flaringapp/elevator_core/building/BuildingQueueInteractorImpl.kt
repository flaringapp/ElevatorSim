package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId
import com.flaringapp.elevator_core.queue.FloorQueue
import com.flaringapp.elevator_core.queue.FloorQueueFactory
import com.flaringapp.elevator_core.utils.removeAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BuildingQueueInteractorImpl(
    private val building: Building,
    private val floorQueueFactory: FloorQueueFactory,
) : BuildingQueueInteractor {

    private val queues: MutableMap<FloorNumber, FloorQueue> = HashMap()

    private val queueFlows: MutableMap<FloorNumber, MutableStateFlow<PersonId?>> = HashMap()

    override fun enterQueue(floor: FloorNumber, person: Person): ElevatorId {
        val queue = resolveQueue(floor)
        val wasQueueEmpty = queue.isEmpty()
        return queue.enterQueue(person).also { elevator ->
            if (wasQueueEmpty) {
                notifyQueueFlow(floor, elevator, queue)
            }
        }
    }

    override fun pollPerson(floor: FloorNumber, elevator: Elevator): PersonId? {
        val queue = requireQueue(floor)
        if (queue.isEmpty()) return null
        return queue.pollPerson(elevator.id).also {
            notifyQueueFlow(floor, elevator.id, queue)
        }
    }

    override fun observeFloorQueue(floor: FloorNumber): StateFlow<PersonId?> {
        return resolveQueueFlow(floor)
    }

    override fun cleanup() {
        queues.forEach { (_, queue) -> queue.cleanup() }
        queueFlows.removeAll { _, value ->
            value.subscriptionCount.value == 0
        }
    }

    private fun resolveQueue(floor: FloorNumber): FloorQueue {
        return queues[floor] ?: createQueue(floor)
    }

    private fun createQueue(floor: FloorNumber): FloorQueue {
        return floorQueueFactory.createQueue(building).also {
            queues[floor] = it
        }
    }

    private fun requireQueue(floor: FloorNumber): FloorQueue {
        return queues[floor] ?: missingQueueError(floor)
    }

    private fun notifyQueueFlow(
        floor: FloorNumber,
        elevator: ElevatorId,
        queue: FloorQueue,
    ) {
        val nextPerson =
            if (queue.isEmpty()) null
            else queue.peekPerson(elevator)
        resolveQueueFlow(floor).tryEmit(nextPerson)
    }

    private fun resolveQueueFlow(floor: FloorNumber): MutableStateFlow<PersonId?> {
        queueFlows[floor]?.let { return it }
        return MutableStateFlow<PersonId?>(null).also {
            queueFlows[floor] = it
        }
    }

    private fun missingQueueError(floor: FloorNumber): Nothing {
        error("Cannot find queue for floor $floor")
    }
}