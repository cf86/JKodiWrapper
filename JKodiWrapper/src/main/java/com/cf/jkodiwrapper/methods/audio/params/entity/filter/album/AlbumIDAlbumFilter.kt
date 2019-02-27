package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

data class AlbumIDAlbumFilter(var albumID: LibraryID) : AbstractAlbumFilter() {

    override fun toJSON(): String {
        return "\"albumid\":$albumID"
    }
}