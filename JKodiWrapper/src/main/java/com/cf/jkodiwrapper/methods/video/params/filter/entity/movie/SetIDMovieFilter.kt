package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

import com.cf.jkodiwrapper.types.library.LibraryID

data class SetIDMovieFilter(var setID: LibraryID) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"setID\":$setID"
    }
}