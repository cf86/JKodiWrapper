package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class SetMovieFilter(var set: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"set\":\"$set\""
    }
}