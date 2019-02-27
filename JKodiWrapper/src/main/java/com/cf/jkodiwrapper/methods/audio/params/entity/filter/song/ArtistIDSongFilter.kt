package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistIDSongFilter(var artistID: LibraryID) : AbstractSongFilter() {

    override fun toJSON(): String {
        return "\"artistid\":$artistID"
    }
}