package com.cf.jkodiwrapper.methods.player.params.entity.stream

import com.cf.jkodiwrapper.types.global.GlobalNxtPrev

data class PrevNextStreamSelection(var prevNext: GlobalNxtPrev) : AbstractStreamSelection() {

    override fun getValue(): String {
        return prevNext.state
    }
}