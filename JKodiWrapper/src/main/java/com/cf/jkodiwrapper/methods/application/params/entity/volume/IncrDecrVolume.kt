package com.cf.jkodiwrapper.methods.application.params.entity.volume

import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement

data class IncrDecrVolume(var incrDecr: GlobalIncrementDecrement) : AbstractVolume() {

    override fun isValid(): Boolean {
        return true
    }

    override fun getValue(): String {
        return incrDecr.param
    }
}