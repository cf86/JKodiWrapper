package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistRoleIDSongFilter(var artist: String,
                                  var roleID: LibraryID) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\",\"roleid\":$roleID"
    }
}