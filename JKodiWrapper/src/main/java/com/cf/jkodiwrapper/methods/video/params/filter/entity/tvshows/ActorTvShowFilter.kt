package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

data class ActorTvShowFilter(var actor: String) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"actor\":\"$actor\""
    }
}