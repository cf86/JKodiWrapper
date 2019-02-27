package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

data class GenreArtistFilter(var genre: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}