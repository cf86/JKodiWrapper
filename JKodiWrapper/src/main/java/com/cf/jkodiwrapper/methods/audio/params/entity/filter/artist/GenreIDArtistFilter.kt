package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDArtistFilter(var genreID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}