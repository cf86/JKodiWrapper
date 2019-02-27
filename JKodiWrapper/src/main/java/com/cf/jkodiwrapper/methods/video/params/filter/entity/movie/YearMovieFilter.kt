package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class YearMovieFilter(var year: Int) : AbstractMovieFilter() {

    init {
        if (year < 0)
            throw IllegalArgumentException("Year must be >= 0.")
    }

    override fun toJSON(): String {
        return "\"year\":$year"
    }
}