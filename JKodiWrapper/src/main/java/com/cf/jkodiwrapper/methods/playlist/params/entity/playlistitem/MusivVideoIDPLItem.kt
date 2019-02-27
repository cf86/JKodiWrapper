package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class MusivVideoIDPLItem(var musicVideoID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"musicvideoid\":$musicVideoID"
    }
}