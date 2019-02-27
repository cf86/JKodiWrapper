package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class RoleSongIDArtistFilter(var role: String,
                                  var songID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"role\":\"$role\",\"songid\":$songID"
    }
}