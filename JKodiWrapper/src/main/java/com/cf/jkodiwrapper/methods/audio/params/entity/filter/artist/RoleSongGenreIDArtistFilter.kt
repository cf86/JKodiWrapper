package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class RoleSongGenreIDArtistFilter(var role: String,
                                       var songGenreID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"role\":\"$role\",\"songgenreid\":$songGenreID"
    }
}