package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class StudioMovieFilter(var studio: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"studio\":\"$studio\""
    }
}