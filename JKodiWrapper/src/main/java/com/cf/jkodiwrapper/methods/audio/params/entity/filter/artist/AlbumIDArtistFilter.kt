package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

data class AlbumIDArtistFilter(var albumID: LibraryID) : AbstractArtistFilter() {

    override fun toJSON(): String {
        return "\"albumid\":$albumID"
    }
}