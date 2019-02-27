package com.cf.jkodiwrapper.methods.playlist

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class PlaylistMethod(var methodName: String) : KodiMethod {

    ADD("Playlist.Add"),
    CLEAR("Playlist.Clear"),
    GET_ITEMS("Playlist.GetItems"),
    GET_PLAYLISTS("Playlist.GetPlaylists"),
    GET_PROPERTIES("Playlist.GetProperties"),
    INSERT("Playlist.Insert"),
    REMOVE("Playlist.Remove"),
    SWAP("Playlist.Swap");

    override fun toString(): String {
        return methodName
    }
}