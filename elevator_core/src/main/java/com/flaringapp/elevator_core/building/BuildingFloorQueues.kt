package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber
import com.flaringapp.elevator_core.person.PersonId
import com.flaringapp.elevator_core.utils.removeAll

@JvmInline
value class BuildingFloorQueues(
    private val queues: MutableMap<FloorNumber, FloorElevatorQueues>,
) {

    constructor() : this(HashMap())
    constructor(floorsCount: Int) : this(HashMap(floorsCount))

    fun enter(floor: FloorNumber, elevator: ElevatorId, person: PersonId) {
        val queue = requireQueue(floor)
        queue.enter(
            elevator = elevator,
            person = person,
        )
    }

    fun exit(floor: FloorNumber, elevator: ElevatorId): PersonId {
        val queue = requireQueue(floor)
        return queue.exit(
            elevator = elevator,
        )
    }

    fun findElevatorWithSmallestQueue(floor: FloorNumber): ElevatorId? {
        return resolveQueue(floor).firstElevatorWithSmallestQueue()
    }

    fun cleanup() {
        queues.removeAll { _, value ->
            value.isEmpty()
        }
    }

    private fun resolveQueue(floor: FloorNumber): FloorElevatorQueues {
        return queues[floor] ?: createQueue(floor)
    }

    private fun createQueue(floor: FloorNumber): FloorElevatorQueues {
        return FloorElevatorQueues().also { queues[floor] = it }
    }

    private fun requireQueue(floor: FloorNumber): FloorElevatorQueues {
        return queues[floor] ?: missingQueueError(floor)
    }

    private fun missingQueueError(floor: FloorNumber): Nothing {
        error("Cannot find queue for floor $floor")
    }
}