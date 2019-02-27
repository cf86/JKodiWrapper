package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

data class GenreMovieFilter(var genre: String) : AbstractMovieFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}