package com.cf.jkodiwrapper.methods.player.params.entity.seekto

import com.cf.jkodiwrapper.types.player.PlayerForwBackw
import com.cf.jkodiwrapper.types.player.PlayerPositionPercentage
import com.cf.jkodiwrapper.types.player.PlayerPositionTime

abstract class AbstractSeekTo {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getSeekBackForward(backForward: PlayerForwBackw) = SeekBackForward(backForward)

        @JvmStatic
        fun getSeekToPercentage(toPercentage: PlayerPositionPercentage) = SeekToPercentage(toPercentage)

        @JvmStatic
        fun getSeekToTime(toTime: PlayerPositionTime) = SeekToTime(toTime)
    }
}