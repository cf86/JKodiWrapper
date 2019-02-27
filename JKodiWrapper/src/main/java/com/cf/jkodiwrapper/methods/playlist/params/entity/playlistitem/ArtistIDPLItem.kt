package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistIDPLItem(var artistID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"artistid\":$artistID"
    }
}