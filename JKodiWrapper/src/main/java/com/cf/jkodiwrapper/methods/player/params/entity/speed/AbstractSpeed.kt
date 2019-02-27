package com.cf.jkodiwrapper.methods.player.params.entity.speed

import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement
import com.cf.jkodiwrapper.types.player.PlayerSpeed

abstract class AbstractSpeed {

    abstract fun getValue(): String

    companion object {
        @JvmStatic
        fun getIncrDecrSpeed(incrDecr: GlobalIncrementDecrement) = IncrDecrSpeed(incrDecr)

        @JvmStatic
        fun getSpeedLevel(speed: PlayerSpeed) = SpeedLevel(speed)
    }
}