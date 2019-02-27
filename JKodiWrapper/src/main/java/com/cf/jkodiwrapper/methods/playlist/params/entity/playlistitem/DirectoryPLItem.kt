package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.files.FilesMedia

data class DirectoryPLItem(var directory: String,
                           var mediaType: FilesMedia = FilesMedia.FILES,
                           var recursive: Boolean = false) : AbstractPlaylistItem() {

    override fun toJSON(): String {
        return "\"directory\":\"$directory\",\"media\":\"$mediaType\",\"recursive\":$recursive"
    }
}