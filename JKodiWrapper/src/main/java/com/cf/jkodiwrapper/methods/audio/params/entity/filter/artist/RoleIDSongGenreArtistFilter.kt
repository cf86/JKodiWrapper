package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class RoleIDSongGenreArtistFilter(var roleID: LibraryID,
                                       var songGenre: String) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"roleid\":$roleID,\"songgenre\":\"$songGenre\""
    }
}