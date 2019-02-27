package com.cf.jkodiwrapper.methods.player.params.entity.seekto

import com.cf.jkodiwrapper.types.player.PlayerForwBackw

data class SeekBackForward(var backForward: PlayerForwBackw) : AbstractSeekTo() {

    override fun toJSON(): String {
        return "\"value\":\"$backForward\""
    }
}