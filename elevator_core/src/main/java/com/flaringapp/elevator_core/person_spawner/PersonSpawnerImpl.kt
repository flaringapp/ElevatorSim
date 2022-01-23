package com.flaringapp.elevator_core.person_spawner

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.person_factory.PersonFactory
import com.flaringapp.elevator_core.person_manager.PersonSimulator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PersonSpawnerImpl(
    private val context: CoroutineContext,
    private val config: PersonSpawnerConfig,
    private val building: Building,
    private val personFactory: PersonFactory,
    private val personSimulator: PersonSimulator,
) : PersonSpawner {

    private var scope: CoroutineScope? = null

    override fun start() {
        scope = CoroutineScope(context).also {
            it.launch {
                spawn()
            }
        }
    }

    override fun stop() {
        scope?.cancel()
    }

    private suspend fun CoroutineScope.spawn() {
        delay(config.initialDelay)
        while (isActive) {
            val person = personFactory.createPerson(building)
            personSimulator.simulate(building, person)
        }
    }
}