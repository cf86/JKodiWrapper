package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class MovieIDPLItem(var movieID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"movieid\":$movieID"
    }
}