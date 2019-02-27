package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

import com.cf.jkodiwrapper.types.library.LibraryID

data class GenreIDMusicVideoFilter(var genreID: LibraryID) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"genreid\":$genreID"
    }
}