package com.flaringapp.elevator_core.building

import com.flaringapp.elevator_core.person.PersonId

@JvmInline
value class ElevatorQueue(private val queue: ArrayDeque<PersonId>) {

    constructor(): this(ArrayDeque())

    val size: Int
        get() = queue.size

    fun isEmpty(): Boolean = queue.isEmpty()

    fun enter(personId: PersonId) {
        queue.addLast(personId)
    }

    fun exit(): PersonId {
        return queue.removeFirst()
    }

}