package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.elevator.Elevator
import com.flaringapp.elevator_core.elevator_behaviour.ElevatorBehaviour
import com.flaringapp.elevator_core.elevator_behaviour.ElevatorBehaviourFactory

class DirectionedElevatorBehaviourFactory(
    private val higherFloorPicker: DirectionedElevatorFloorPicker,
    private val lowerFloorPicker: DirectionedElevatorFloorPicker,
) : ElevatorBehaviourFactory {

    override fun createBehaviour(elevator: Elevator): ElevatorBehaviour {
        return DirectionedElevatorBehaviourImpl(
            higherFloorPicker = higherFloorPicker,
            lowerFloorPicker = lowerFloorPicker,
            elevator = elevator,
        )
    }
}