package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

data class AlbumSongFilter(var album: String) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"album\":\"$album\""
    }
}