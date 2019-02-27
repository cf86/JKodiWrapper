package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

data class AlbumIDSongFilter(var albumID: LibraryID) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"albumid\":$albumID"
    }
}