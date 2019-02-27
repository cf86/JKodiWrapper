package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.playlist.PlaylistItem

data class PlaylistItemMedia(var playlistItem: PlaylistItem) : AbstractPlayerMedia() {

    override fun toJSON(): String {
        return "\"file\":\"${playlistItem.file}\""
    }
}