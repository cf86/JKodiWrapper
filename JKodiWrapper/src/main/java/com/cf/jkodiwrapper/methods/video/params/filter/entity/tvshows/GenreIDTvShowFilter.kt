package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDTvShowFilter(var genreID: LibraryID) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}