package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter

data class ActorEpisodesFilter(var actor: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"actor\":\"$actor\""
    }
}