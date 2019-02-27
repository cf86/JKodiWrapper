package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter

data class DirectorEpisodesFilter(var director: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"director\":\"$director\""
    }
}