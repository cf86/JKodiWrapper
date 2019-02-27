package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.player.PlayerType

data class PartyModeMedia(var smartPlaylistPath: String?, var playerType: PlayerType? = null) : AbstractPlayerMedia() {

    override fun toJSON(): String {
        return "\"partymode\":${if (smartPlaylistPath != null) "\"$smartPlaylistPath\"" else "\"$playerType\""}"
    }
}