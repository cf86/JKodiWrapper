package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

data class ArtistAlbumFilter(var artist: String) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\""
    }
}