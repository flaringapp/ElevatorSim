package com.flaringapp.elevator_core.person_factory

import com.flaringapp.elevator_core.person.PersonId
import java.util.concurrent.atomic.AtomicInteger

interface PersonIdFactory {

    fun createId(): PersonId

}

class SynchronizedPersonIdFactory : PersonIdFactory {

    private val idCounter = AtomicInteger()

    override fun createId(): PersonId {
        return PersonId(idCounter.getAndIncrement())
    }
}