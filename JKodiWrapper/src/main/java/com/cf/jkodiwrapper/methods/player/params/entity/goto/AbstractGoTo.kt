package com.cf.jkodiwrapper.methods.player.params.entity.goto

import com.cf.jkodiwrapper.types.global.GlobalNxtPrev
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition

abstract class AbstractGoTo {

    abstract fun getValue(): String

    companion object {
        @JvmStatic
        fun getNxtPrevGoTo(nxtPrev: GlobalNxtPrev) = NxtPrevGoTo(nxtPrev)

        @JvmStatic
        fun getPlaylistPosGoTo(playlistPos: PlaylistPosition) = PlaylistPosGoTo(playlistPos)
    }
}