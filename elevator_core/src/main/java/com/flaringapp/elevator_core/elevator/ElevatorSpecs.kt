package com.flaringapp.elevator_core.elevator

import com.flaringapp.elevator_core.common.Acceleration
import com.flaringapp.elevator_core.common.Speed
import com.flaringapp.elevator_core.common.Weight

data class ElevatorSpecs(
    val maxWeight: Weight,
    val maxSpeed: Speed,
    val acceleration: Acceleration,
    val deceleration: Acceleration,
)