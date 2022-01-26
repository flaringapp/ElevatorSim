@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.flaringapp.elevator_core.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class Flow_extKtTest {

    @Test
    fun first() = runTest {
        val flow = flow {
            emit(10)
            delay(100)
            emit(20)
            delay(100)
            emit(30)
        }

        flow.collectUntil { it == 30 }
    }

}