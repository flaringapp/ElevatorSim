package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.queue.FloorQueue
import com.flaringapp.elevator_core.queue.FloorQueueFactory

class DirectionedFloorQueueFactory : FloorQueueFactory {

    override fun createQueue(building: Building): FloorQueue {
        return DirectionedFloorQueue(building)
    }
}