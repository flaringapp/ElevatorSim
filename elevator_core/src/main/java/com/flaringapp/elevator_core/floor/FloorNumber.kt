package com.flaringapp.elevator_core.floor

@JvmInline
value class FloorNumber(val value: Int) : Comparable<FloorNumber> {

    override fun compareTo(other: FloorNumber): Int {
        return value.compareTo(other.value)
    }
}