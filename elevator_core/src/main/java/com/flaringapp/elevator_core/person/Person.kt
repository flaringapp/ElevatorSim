package com.flaringapp.elevator_core.person

import com.flaringapp.elevator_core.common.Weight

data class Person(
    val id: PersonId,
    val name: String,
    val weight: Weight,
    val trip: PersonTrip,
)
