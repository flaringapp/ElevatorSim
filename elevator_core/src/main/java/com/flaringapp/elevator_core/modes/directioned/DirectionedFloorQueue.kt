package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.building.OrderedElevatorQueue
import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.person.Person
import com.flaringapp.elevator_core.person.PersonId
import com.flaringapp.elevator_core.queue.FloorQueue
import com.flaringapp.elevator_core.utils.removeAll

class DirectionedFloorQueue(
    private val building: Building,
) : FloorQueue {

    private val queues: LinkedHashMap<ElevatorId, OrderedElevatorQueue> = LinkedHashMap()

    override fun enterQueue(person: Person): ElevatorId {
        val elevator = findElevatorToEnter()
        val queue = resolveQueue(elevator)
        queue.enter(person.id)
        return elevator
    }

    override fun pollPerson(elevator: ElevatorId): PersonId {
        val elevatorQueue = requireQueue(elevator)
        return elevatorQueue.exit()
    }

    override fun peekPerson(elevator: ElevatorId): PersonId {
        val elevatorQueue = requireQueue(elevator)
        return elevatorQueue.head()
    }

    override fun isEmpty(): Boolean {
        return queues.all { it.value.isEmpty() }
    }

    override fun cleanup() {
        queues.removeAll { _, value ->
            value.isEmpty()
        }
    }

    private fun findElevatorToEnter(): ElevatorId {
        return firstElevatorWithSmallestQueue() ?: building.firstElevator().id
    }

    private fun firstElevatorWithSmallestQueue(): ElevatorId? {
        return queues.minByOrNull { it.value.size }?.key
    }

    private fun resolveQueue(elevator: ElevatorId): OrderedElevatorQueue {
        return queues[elevator] ?: createQueue(elevator)
    }

    private fun createQueue(elevator: ElevatorId): OrderedElevatorQueue {
        return OrderedElevatorQueue().also { queues[elevator] = it }
    }

    private fun requireQueue(elevator: ElevatorId): OrderedElevatorQueue {
        return queues[elevator] ?: missingQueueError(elevator)
    }

    private fun missingQueueError(elevator: ElevatorId): Nothing {
        error("Cannot find queue for elevator $elevator")
    }
}