package com.flaringapp.elevator_core.queue

import com.flaringapp.elevator_core.building.Building

interface FloorQueueProvider {

    fun createQueue(building: Building): FloorQueue

}