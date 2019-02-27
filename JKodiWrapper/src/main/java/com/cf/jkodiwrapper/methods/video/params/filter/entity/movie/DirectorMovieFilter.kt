package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class DirectorMovieFilter(var director: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"director\":\"$director\""
    }
}