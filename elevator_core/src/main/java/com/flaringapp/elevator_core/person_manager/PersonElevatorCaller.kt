package com.flaringapp.elevator_core.person_manager

import com.flaringapp.elevator_core.floor.FloorNumber

interface PersonElevatorCaller {

    fun callOutside(initialFloor: FloorNumber, targetFloor: FloorNumber)

    fun callInside(targetFloor: FloorNumber)

}