package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

data class StudioTvShowFilter(var studio: String) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"studio\":\"$studio\""
    }
}