package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class AlbumIDPLItem(var albumID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"albumid\":$albumID"
    }
}