package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.common.Direction
import com.flaringapp.elevator_core.elevator_panel.ElevatorPanel
import com.flaringapp.elevator_core.floor.FloorNumber

interface DirectionedElevatorPanel : ElevatorPanel {

    fun call(floor: FloorNumber, direction: Direction)

}

class DirectionedElevatorPanelImpl(
    private val elevatorBehaviour: DirectionedElevatorBehaviour
) : DirectionedElevatorPanel {

    private val callTransformer = ElevatorCallTransformer(elevatorBehaviour)

    override fun requestFloor(floor: FloorNumber) {
        elevatorBehaviour.requestTargetFloor(floor)
    }

    override fun call(floor: FloorNumber, direction: Direction) {
        direction.transform(callTransformer.withFloor(floor))
    }

    private class ElevatorCallTransformer(
        private val elevatorBehaviour: DirectionedElevatorBehaviour,
    ) : Direction.Transformer<Unit> {

        private var floor: FloorNumber? = null

        fun withFloor(floor: FloorNumber) = apply {
            this.floor = floor
        }

        override fun upwards() {
            floor?.let { elevatorBehaviour.callUpwards(it) }
        }

        override fun downwards() {
            floor?.let { elevatorBehaviour.callDownwards(it) }
        }
    }
}