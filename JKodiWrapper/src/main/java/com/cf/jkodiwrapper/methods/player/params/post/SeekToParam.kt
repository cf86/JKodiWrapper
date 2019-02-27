package com.cf.jkodiwrapper.methods.player.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.player.params.entity.seekto.AbstractSeekTo
import com.cf.jkodiwrapper.types.player.PlayerID

data class SeekToParam(var playerID: PlayerID,
                       var seekTo: AbstractSeekTo) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"playerid\":$playerID,${seekTo.toJSON()}"
    }
}