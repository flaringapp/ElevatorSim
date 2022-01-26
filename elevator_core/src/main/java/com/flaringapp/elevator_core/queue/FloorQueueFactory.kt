package com.flaringapp.elevator_core.queue

import com.flaringapp.elevator_core.building.Building

interface FloorQueueFactory {

    fun createQueue(building: Building): FloorQueue

}