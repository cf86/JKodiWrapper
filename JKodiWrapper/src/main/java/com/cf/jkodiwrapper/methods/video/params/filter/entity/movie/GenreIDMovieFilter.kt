package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDMovieFilter(var genreID: LibraryID) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}