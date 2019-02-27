package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class SongGenreIDArtistFilter(var genreID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"songgenreid\":$genreID"
    }
}