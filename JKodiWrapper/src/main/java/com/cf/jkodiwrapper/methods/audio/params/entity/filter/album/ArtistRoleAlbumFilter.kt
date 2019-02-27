package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

data class ArtistRoleAlbumFilter(var artist: String,
                                 var role: String) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\",\"role\":\"$role\""
    }
}