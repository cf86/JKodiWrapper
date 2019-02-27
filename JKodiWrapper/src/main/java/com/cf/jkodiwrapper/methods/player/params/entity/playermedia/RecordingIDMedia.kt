package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.library.LibraryID

data class RecordingIDMedia(var recordingID: LibraryID) : AbstractPlayerMedia() {

    override fun toJSON(): String {
        return "\"recordingid\":${recordingID.id}"
    }
}