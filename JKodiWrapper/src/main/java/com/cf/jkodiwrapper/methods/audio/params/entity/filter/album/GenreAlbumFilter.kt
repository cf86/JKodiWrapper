package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

data class GenreAlbumFilter(var genre: String) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}