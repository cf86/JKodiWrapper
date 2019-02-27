package com.cf.jkodiwrapper.methods.playlist.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem.AbstractPlaylistItem
import com.cf.jkodiwrapper.types.playlist.PlaylistID

data class AddPlaylistItemParam(var playlistID: PlaylistID,
                                var items: List<AbstractPlaylistItem>) : KodiPostParam() {

    override fun toJSON(): String {
        return if (items.size == 1)
            "\"playlistid\":$playlistID,\"item\":{${items[0].toJSON()}}"
        else "\"playlistid\":$playlistID,\"item\":[${items.joinToString(",") { "{${it.toJSON()}}" }}]"
    }
}