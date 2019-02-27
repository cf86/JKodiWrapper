package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

data class YearTvShowFilter(var year: Int) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"year\":$year"
    }
}