package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistIDRoleIDAlbumFilter(var artistID: LibraryID,
                                     var roleID: LibraryID) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"artistid\":$artistID,\"roleid\":$roleID"
    }
}