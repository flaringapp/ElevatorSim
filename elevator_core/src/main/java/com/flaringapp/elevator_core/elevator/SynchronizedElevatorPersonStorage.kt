package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.common.Weight
import com.flaringapp.elevator_core.person.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.coroutines.CoroutineContext

class SynchronizedElevatorPersonStorage(
    context: CoroutineContext
) : ElevatorPersonStorage {

    private val lock = ReentrantLock()
    private val scope = CoroutineScope(context)

    private val people = HashSet<Person>()

    override var totalWeight: Weight = Weight(0f)
        set(value) {
            lock.withLock {
                field = value
            }
        }

    override val changedFlow = MutableSharedFlow<Unit>()

    override fun addPerson(person: Person) {
        lock.withLock {
            people += person
            totalWeight += person.weight
        }
    }

    override fun removePerson(person: Person) {
        lock.withLock {
            people -= person
            totalWeight -= person.weight
        }
    }

    private fun notifyChanged() {
        scope.launch {
            changedFlow.emit(Unit)
        }
    }
}