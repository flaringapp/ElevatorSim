package com.flaringapp.elevator_core.floor

@JvmInline
value class FloorNumber(val value: Int) : Comparable<FloorNumber> {

    override fun compareTo(other: FloorNumber): Int {
        return value.compareTo(other.value)
    }

    operator fun plus(other: FloorNumber): FloorNumber {
        return FloorNumber(value + other.value)
    }

    operator fun minus(other: FloorNumber): FloorNumber {
        return FloorNumber(value - other.value)
    }


    operator fun plus(amount: Int): FloorNumber {
        return FloorNumber(value + amount)
    }

    operator fun minus(amount: Int): FloorNumber {
        return FloorNumber(value - amount)
    }
}