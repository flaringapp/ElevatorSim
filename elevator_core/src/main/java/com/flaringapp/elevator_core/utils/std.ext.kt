@file:Suppress("unused")

package com.flaringapp.elevator_core.utils

import java.util.concurrent.locks.Lock
import kotlin.concurrent.withLock

/**
 * Removes all elements from this [MutableMap] that match the given [predicate].
 *
 * @return `true` if any element was removed from this map, or `false` when no elements were removed and map was not modified.
 */
inline fun <T, U> MutableMap<T, U>.removeAll(predicate: (key: T, value: U) -> Boolean): Boolean {
    return filterInPlace(predicate, true)
}

/**
 * Retains only elements of this [MutableMap] that match the given [predicate].
 *
 * @return `true` if any element was removed from this map, or `false` when all elements were retained and map was not modified.
 */
inline fun <T, U> MutableMap<T, U>.retainAll(predicate: (key: T, value: U) -> Boolean): Boolean {
    return filterInPlace(predicate, false)
}

inline fun <T, U> MutableMap<T, U>.filterInPlace(
    predicate: (key: T, value: U) -> Boolean,
    predicateResultToRemove: Boolean,
): Boolean {
    var result = false
    with(iterator()) {
        while (hasNext()) {
            val entry = next()
            if (predicate(entry.key, entry.value) == predicateResultToRemove) {
                remove()
                result = true
            }
        }
    }
    return result
}

inline fun Lock.use(action: () -> Unit) {
    withLock(action)
}