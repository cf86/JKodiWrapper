package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class RoleIDSongGenreIDArtistFilter(var roleID: LibraryID,
                                         var songGenreID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"roleid\":$roleID,\"songgenreid\":$songGenreID"
    }
}