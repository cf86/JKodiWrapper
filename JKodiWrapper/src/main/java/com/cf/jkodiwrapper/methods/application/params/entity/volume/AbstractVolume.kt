package com.cf.jkodiwrapper.methods.application.params.entity.volume

import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement

abstract class AbstractVolume {

    abstract fun getValue(): String

    abstract fun isValid(): Boolean

    companion object {
        @JvmStatic
        fun getChangeVolume(newLevel: Int) = LevelVolume(newLevel)

        @JvmStatic
        fun getIncrDecrVolume(incrDecr: GlobalIncrementDecrement) = IncrDecrVolume(incrDecr)
    }
}