package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class RoleIDArtistFilter(var roleID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"roleid\":$roleID"
    }
}