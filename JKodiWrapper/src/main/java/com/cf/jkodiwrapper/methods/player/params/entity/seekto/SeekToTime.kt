package com.cf.jkodiwrapper.methods.player.params.entity.seekto

import com.cf.jkodiwrapper.types.player.PlayerPositionTime

data class SeekToTime(var toTime: PlayerPositionTime) : AbstractSeekTo() {

    override fun toJSON(): String {
        return "\"value\":{\"hours\":${toTime.hours},\"minutes\":${toTime.minutes},\"seconds\":${toTime.seconds},\"milliseconds\":${toTime.milliseconds}}"
    }
}