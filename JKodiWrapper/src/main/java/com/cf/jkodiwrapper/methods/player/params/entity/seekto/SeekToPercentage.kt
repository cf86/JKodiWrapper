package com.cf.jkodiwrapper.methods.player.params.entity.seekto

import com.cf.jkodiwrapper.types.player.PlayerPositionPercentage

data class SeekToPercentage(var toPercentage: PlayerPositionPercentage) : AbstractSeekTo() {

    override fun toJSON(): String {
        return "\"value\":$toPercentage"
    }
}