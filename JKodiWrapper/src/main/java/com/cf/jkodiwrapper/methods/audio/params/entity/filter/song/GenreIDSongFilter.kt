package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDSongFilter(var genreID: LibraryID) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}