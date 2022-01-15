package com.flaringapp.elevator_core.queue_picker

import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber

interface ElevatorQueuePicker {

    fun pick(floor: FloorNumber): ElevatorId

}