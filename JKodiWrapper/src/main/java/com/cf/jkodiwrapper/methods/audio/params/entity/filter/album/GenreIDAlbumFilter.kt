package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDAlbumFilter(var genreID: LibraryID) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}