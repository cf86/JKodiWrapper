package com.cf.jkodiwrapper.methods.playlist.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem.AbstractPlaylistItem
import com.cf.jkodiwrapper.types.playlist.PlaylistID
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition

data class InsertPlaylistItemParam(var playlistID: PlaylistID,
                                   var position: PlaylistPosition,
                                   var items: List<AbstractPlaylistItem>) : KodiPostParam() {

    override fun toJSON(): String {
        return if (items.size == 1)
            "\"playlistid\":$playlistID,\"position\":$position,\"item\":{${items[0].toJSON()}}"
        else "\"playlistid\":$playlistID,\"position\":$position,\"item\":[${items.joinToString(",") { "{${it.toJSON()}}" }}]"
    }
}