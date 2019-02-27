package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

data class TagTvShowFilter(var tag: String) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"tag\":\"$tag\""
    }
}