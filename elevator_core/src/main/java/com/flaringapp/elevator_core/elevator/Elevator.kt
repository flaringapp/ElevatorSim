package com.flaringapp.elevator_core.elevator

data class Elevator(
    val id: ElevatorId,
    val name: String,
    val specs: ElevatorSpecs,
    val state: ElevatorState,
)