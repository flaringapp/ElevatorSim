package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.person.PersonId
import com.flaringapp.elevator_core.utils.removeAll

@JvmInline
value class FloorElevatorQueues(
    private val queues: LinkedHashMap<ElevatorId, ElevatorQueue>,
) {

    constructor() : this(LinkedHashMap())

    fun isEmpty(): Boolean {
        return queues.all { entry ->
            entry.value.isEmpty()
        }
    }

    fun enter(elevator: ElevatorId, person: PersonId) {
        requireQueue(elevator).enter(person)
    }

    fun exit(elevator: ElevatorId): PersonId {
        return requireQueue(elevator).exit()
    }

    fun firstElevatorWithSmallestQueue(): ElevatorId? {
        return queues.minByOrNull { it.value.size }?.key
    }

    fun cleanup() {
        queues.removeAll { _, value ->
            value.isEmpty()
        }
    }

    private fun resolveQueue(elevator: ElevatorId): ElevatorQueue {
        return queues[elevator] ?: createQueue(elevator)
    }

    private fun createQueue(elevator: ElevatorId): ElevatorQueue {
        return ElevatorQueue().also { queues[elevator] = it }
    }

    private fun requireQueue(elevator: ElevatorId): ElevatorQueue {
        return queues[elevator] ?: missingQueueError(elevator)
    }

    private fun missingQueueError(elevator: ElevatorId): Nothing {
        error("Cannot find queue for elevator $elevator")
    }

}