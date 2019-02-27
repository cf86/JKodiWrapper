package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDEpisodesFilter(var genreID: LibraryID) : AbstractEpisodesFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}