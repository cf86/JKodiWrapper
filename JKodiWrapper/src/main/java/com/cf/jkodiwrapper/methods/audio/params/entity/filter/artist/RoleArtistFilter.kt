package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

data class RoleArtistFilter(var role: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"role\":\"$role\""
    }
}