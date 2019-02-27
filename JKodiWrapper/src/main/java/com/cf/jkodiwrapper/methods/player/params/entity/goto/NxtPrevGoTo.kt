package com.cf.jkodiwrapper.methods.player.params.entity.goto

import com.cf.jkodiwrapper.types.global.GlobalNxtPrev

data class NxtPrevGoTo(var nxtPrev: GlobalNxtPrev) : AbstractGoTo() {

    override fun getValue(): String {
        return nxtPrev.state
    }
}