package com.flaringapp.elevator_core.person_factory

import com.flaringapp.elevator_core.common.Weight
import kotlin.random.Random

interface PersonWeightFactory {

    fun createWeight(): Weight

}

class DefaultPersonWeightFactory : PersonWeightFactory {

    companion object {
        private const val WEIGHT_MIN = 35.0
        private const val WEIGHT_MAX = 100.0
    }

    override fun createWeight(): Weight {
        val weight = Random.nextDouble(WEIGHT_MIN, WEIGHT_MAX).toFloat()
        return Weight(weight)
    }
}