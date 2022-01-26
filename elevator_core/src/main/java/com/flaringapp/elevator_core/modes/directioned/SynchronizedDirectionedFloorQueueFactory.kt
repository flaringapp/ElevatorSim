package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.building.Building
import com.flaringapp.elevator_core.queue.FloorQueue
import com.flaringapp.elevator_core.queue.FloorQueueFactory

class SynchronizedDirectionedFloorQueueFactory : FloorQueueFactory {

    override fun createQueue(building: Building): FloorQueue {
        return SynchronizedDirectionedFloorQueue(building)
    }
}