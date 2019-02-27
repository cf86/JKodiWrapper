package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class TagMovieFilter(var tag: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"tag\":\"$tag\""
    }
}