package com.cf.jkodiwrapper.methods.player.params.entity.stream

import com.cf.jkodiwrapper.types.global.GlobalNxtPrev

abstract class AbstractStreamSelection {

    abstract fun getValue(): String

    companion object {
        @JvmStatic
        fun getPrevNextStream(prevNext: GlobalNxtPrev) = PrevNextStreamSelection(prevNext)

        @JvmStatic
        fun getIndexStream(index: Int) = StreamIndexSelection(index)
    }
}