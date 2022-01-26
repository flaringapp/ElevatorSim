@file:OptIn(ExperimentalContracts::class)
@file:Suppress("unused")

package com.flaringapp.elevator_core.utils

import kotlinx.coroutines.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

suspend fun <R> cancellableCoroutineScope(block: suspend CoroutineScope.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runCancellable {
        coroutineScope(block)
    }
}

suspend fun <R> cancellableSupervisorScope(block: suspend CoroutineScope.() -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runCancellable {
        supervisorScope(block)
    }
}

fun CoroutineScope.cancellableLaunch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return launch(context, start) {
        runCancellable { block() }
    }
}

suspend fun <T> Deferred<T>.cancellableAwait(): T? {
    return runCancellable {
        await()
    }
}

suspend fun <T> cancellableWithContext(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T? {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return cancellableCoroutineScope {
        withContext(context, block)
    }
}

private inline fun <R> runCancellable(block: () -> R): R? {
    return try {
        block()
    } catch (e: CancellationException) {
        null
    }
}