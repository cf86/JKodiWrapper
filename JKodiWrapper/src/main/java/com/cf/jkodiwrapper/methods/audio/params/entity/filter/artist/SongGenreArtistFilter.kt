package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

data class SongGenreArtistFilter(var songGenre: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"songgenre\":\"$songGenre\""
    }
}