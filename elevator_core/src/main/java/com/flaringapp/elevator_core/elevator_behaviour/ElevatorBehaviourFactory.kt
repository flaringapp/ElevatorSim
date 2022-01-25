package com.flaringapp.elevator_core.elevator_behaviour

import com.flaringapp.elevator_core.elevator.Elevator

interface ElevatorBehaviourFactory {

    fun createBehaviour(elevator: Elevator): ElevatorBehaviour

}