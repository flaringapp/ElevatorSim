package com.flaringapp.elevator_core.elevator_behaviour

import com.flaringapp.elevator_core.floor.FloorNumber

interface ElevatorBehaviour {

    fun chooseNextFloor(currentFloor: FloorNumber): FloorNumber?

    fun processFloorReached(floor: FloorNumber)

    fun requestTargetFloor(floor: FloorNumber)

}