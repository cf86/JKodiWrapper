package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.playlist.PlaylistID
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition

data class PlaylistIDPosMedia(var id: PlaylistID, var position: PlaylistPosition) : AbstractPlayerMedia() {

    override fun toJSON(): String {
        return "\"playlistid\":$id,\"position\":$position"
    }
}