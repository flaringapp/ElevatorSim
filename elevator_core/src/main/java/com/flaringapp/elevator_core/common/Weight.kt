package com.flaringapp.elevator_core.common

@JvmInline
value class Weight(val value: Float): Comparable<Weight> {

    override fun compareTo(other: Weight): Int {
        return value.compareTo(other.value)
    }

    operator fun plus(other: Weight): Weight {
        return Weight(value + other.value)
    }

    operator fun minus(other: Weight): Weight {
        return Weight(value - other.value)
    }
}