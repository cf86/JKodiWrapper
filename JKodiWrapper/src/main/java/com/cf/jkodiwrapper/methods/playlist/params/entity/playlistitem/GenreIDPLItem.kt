package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDPLItem(var genreID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}