package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistRoleIDAlbumFilter(var artist: String,
                                   var roleID: LibraryID) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\",\"roleid\":$roleID"
    }
}