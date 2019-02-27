package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

data class ArtistRoleSongFilter(var artist: String,
                                var role: String) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\",\"role\":\"$role\""
    }
}