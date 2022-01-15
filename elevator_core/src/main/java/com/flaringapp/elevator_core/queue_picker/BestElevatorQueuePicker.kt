package com.flaringapp.elevator_core.queue_picker

import com.flaringapp.elevator_core.building.BuildingQueueInteractor
import com.flaringapp.elevator_core.elevator.ElevatorId
import com.flaringapp.elevator_core.floor.FloorNumber

class BestElevatorQueuePicker(
    private val buildingQueueInteractor: BuildingQueueInteractor
) : ElevatorQueuePicker {

    override fun pick(floor: FloorNumber): ElevatorId {
        return buildingQueueInteractor.findElevatorWithSmallestQueue(floor)
    }
}