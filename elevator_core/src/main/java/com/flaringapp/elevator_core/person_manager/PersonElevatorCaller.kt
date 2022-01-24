package com.flaringapp.elevator_core.person_manager

import com.flaringapp.elevator_core.floor.FloorNumber

interface PersonElevatorCaller {

    fun call(initialFloor: FloorNumber, targetFloor: FloorNumber)

}