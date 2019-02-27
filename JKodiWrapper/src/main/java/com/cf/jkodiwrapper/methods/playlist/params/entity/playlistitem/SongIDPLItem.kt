package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class SongIDPLItem(var songID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"songid\":$songID"
    }
}