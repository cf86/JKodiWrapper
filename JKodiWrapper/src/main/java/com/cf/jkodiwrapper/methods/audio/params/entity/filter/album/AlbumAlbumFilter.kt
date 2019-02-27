package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

data class AlbumAlbumFilter(var album: String) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"album\":\"$album\""
    }
}