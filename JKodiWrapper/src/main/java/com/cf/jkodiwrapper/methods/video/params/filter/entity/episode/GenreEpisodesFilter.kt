package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter

data class GenreEpisodesFilter(var genre: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}