package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

data class GenreSongFilter(var genre: String) : AbstractSongFilter() {
    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}