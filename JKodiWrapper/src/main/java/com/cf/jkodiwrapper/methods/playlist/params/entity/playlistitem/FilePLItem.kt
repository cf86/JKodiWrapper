package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

data class FilePLItem(var path: String) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"file\":\"$path\""
    }
}