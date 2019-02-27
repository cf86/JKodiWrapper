package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.library.LibraryID

data class EpisodeIDPLItem(var episodeID: LibraryID) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"episodeid\":$episodeID"
    }
}