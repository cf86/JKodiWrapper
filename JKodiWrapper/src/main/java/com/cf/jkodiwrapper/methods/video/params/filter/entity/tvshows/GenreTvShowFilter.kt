package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

data class GenreTvShowFilter(var genre: String) : AbstractTvShowFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}