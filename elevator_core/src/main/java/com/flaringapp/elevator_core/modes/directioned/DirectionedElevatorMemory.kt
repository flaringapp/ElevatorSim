package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.floor.FloorNumber
import java.util.*

interface DirectionedElevatorMemory {
    val calledUpFloors: NavigableSet<FloorNumber>
    val calledDownFloors: NavigableSet<FloorNumber>
    val targetFloors: NavigableSet<FloorNumber>
}