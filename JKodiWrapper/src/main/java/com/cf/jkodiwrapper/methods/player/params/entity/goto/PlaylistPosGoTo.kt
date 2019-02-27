package com.cf.jkodiwrapper.methods.player.params.entity.goto

import com.cf.jkodiwrapper.types.playlist.PlaylistPosition

data class PlaylistPosGoTo(var playlistPos: PlaylistPosition) : AbstractGoTo() {

    override fun getValue(): String {
        return playlistPos.pos.toString()
    }
}