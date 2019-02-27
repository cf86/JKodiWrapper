package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.library.LibraryID

data class ChannelIDMedia(var channelID: LibraryID) : AbstractPlayerMedia() {

    override fun toJSON(): String {
        return "\"channelid\":${channelID.id}"
    }
}