package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter

data class YearEpisodesFilter(var year: Int) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"year\":$year"
    }
}