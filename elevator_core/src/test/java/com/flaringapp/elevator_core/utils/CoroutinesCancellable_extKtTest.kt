@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.flaringapp.elevator_core.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

/**
 * Here we test all extension methods that run coroutines with cancellation safety. So we expect
 * each method not to throw [kotlinx.coroutines.CancellationException]
 */
class CoroutinesCancellable_extKtTest {

    @Test
    fun cancellableCoroutineScope() = runTest {
        val result = cancellableCoroutineScope {
            launch {
                awaitCancellation()
            }
            delay(1000)
            cancel()
        }

        delay(1000)

        assertNull(result)
    }

    @Test
    fun cancellableSupervisorScope() = runTest {
        val result = cancellableSupervisorScope {
            launch {
                delay(500)
                cancel()
            }
            launch {
                awaitCancellation()
            }
            delay(1000)
            cancel()
        }

        delay(1000)

        assertNull(result)
    }

    @Test
    fun cancellableLaunch() = runTest {
        cancellableLaunch {
            delay(1000)
            cancel()
        }.join()

        delay(1000)
    }

    @Test
    fun cancellableAsync() = runTest {
        val request = cancellableCoroutineScope {
            async {
                delay(2000)
                this.cancel()
            }
        }

        delay(1000)

        assertNotNull(request)

        val result = request?.cancellableAwait()

        assertNull(result)
    }

    @Test
    fun cancellableWithContext() = runTest {
        val result = cancellableWithContext(coroutineContext) {
            delay(1000)
            cancel()
        }

        delay(1000)

        assertNull(result)
    }
}