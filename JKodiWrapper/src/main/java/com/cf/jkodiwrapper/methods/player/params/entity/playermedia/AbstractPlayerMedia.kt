package com.cf.jkodiwrapper.methods.player.params.entity.playermedia

import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.playlist.PlaylistID
import com.cf.jkodiwrapper.types.playlist.PlaylistItem
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition

abstract class AbstractPlayerMedia {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getChannelIDMedia(channelID: LibraryID) = ChannelIDMedia(channelID)

        @JvmStatic
        fun getPartyModeMedia(smartPlaylistPath: String) = PartyModeMedia(smartPlaylistPath, null)

        @JvmStatic
        fun getPlaylistMedia(id: PlaylistID, position: PlaylistPosition) = PlaylistIDPosMedia(id, position)

        @JvmStatic
        fun getPlaylistItemMedia(playlistItem: PlaylistItem) = PlaylistItemMedia(playlistItem)

        @JvmStatic
        fun getRecordingIDMedia(recordingID: LibraryID) = RecordingIDMedia(recordingID)
    }
}