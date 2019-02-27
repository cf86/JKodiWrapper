package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

data class RoleSongGenreArtistFilter(var role: String,
                                     var songGenre: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"role\":\"$role\",\"songgenre\":\"$songGenre\""
    }
}