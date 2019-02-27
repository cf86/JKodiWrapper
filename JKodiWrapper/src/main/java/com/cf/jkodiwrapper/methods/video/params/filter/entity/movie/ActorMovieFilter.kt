package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class ActorMovieFilter(var actor: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"actor\":\"$actor\""
    }
}