package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class CountryMovieFilter(var country: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"country\":\"$country\""
    }
}