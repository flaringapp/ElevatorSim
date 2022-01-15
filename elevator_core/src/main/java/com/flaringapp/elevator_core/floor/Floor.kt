package com.flaringapp.elevator_core.floor

import com.flaringapp.elevator_core.common.Height

data class Floor(
    val number: FloorNumber,
    val height: Height,
)