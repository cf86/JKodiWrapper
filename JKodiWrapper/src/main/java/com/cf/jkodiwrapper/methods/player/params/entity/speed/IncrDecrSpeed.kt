package com.cf.jkodiwrapper.methods.player.params.entity.speed

import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement

data class IncrDecrSpeed(var incrDecr: GlobalIncrementDecrement) : AbstractSpeed() {

    override fun getValue(): String {
        return incrDecr.param
    }
}