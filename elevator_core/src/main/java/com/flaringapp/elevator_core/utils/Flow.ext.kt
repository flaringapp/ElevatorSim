package com.flaringapp.elevator_core.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable

suspend fun <T> Flow<T>.collectUntil(condition: (T) -> Boolean) {
    cancellableCoroutineScope {
        cancellable().collect {
            if (!condition(it)) return@collect
            cancel()
        }
    }
}