package com.flaringapp.elevator_core.common

import com.flaringapp.elevator_core.elevator.Elevator

sealed interface Direction {

    interface Transformer<out T> {
        fun upwards(): T
        fun downwards(): T
    }

    fun <T> transform(transformer: Transformer<T>): T

    object Upwards : Direction {
        override fun <T> transform(transformer: Transformer<T>): T {
            return transformer.upwards()
        }
    }

    object Downwards : Direction {
        override fun <T> transform(transformer: Transformer<T>): T {
            return transformer.downwards()
        }
    }
}

fun <T> Direction.Transformer<T>.transformFor(elevator: Elevator): T {
    return elevator.state.movementDirection.transform(this)
}