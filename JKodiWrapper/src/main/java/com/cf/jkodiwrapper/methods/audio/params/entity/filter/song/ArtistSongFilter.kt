package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

data class ArtistSongFilter(var artist: String) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\""
    }
}