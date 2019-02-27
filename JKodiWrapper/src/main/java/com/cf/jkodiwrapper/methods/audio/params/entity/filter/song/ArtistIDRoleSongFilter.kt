package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistIDRoleSongFilter(var artistID: LibraryID,
                                  var role: String) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"artistid\":$artistID,\"role\":$role"
    }
}