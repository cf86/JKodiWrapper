package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

data class AlbumArtistFilter(var album: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"album\":\"$album\""
    }
}