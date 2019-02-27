package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistIDRoleAlbumFilter(var artistID: LibraryID,
                                   var role: String) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"artistid\":$artistID,\"role\":\"$role\""
    }
}