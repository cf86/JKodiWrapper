package com.cf.jkodiwrapper.methods.audio

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class AudioMethod(var methodName: String) : KodiMethod {

    CLEAN("AudioLibrary.Clean"),
    EXPORT("AudioLibrary.Export"),
    GET_ALBUM_DETAILS("AudioLibrary.GetAlbumDetails"),
    GET_ALBUMS("AudioLibrary.GetAlbums"),
    GET_ARTIST_DETAILS("AudioLibrary.GetArtistDetails"),
    GET_ARTISTS("AudioLibrary.GetArtists"),
    GET_GENRES("AudioLibrary.getGenres"),
    GET_PROPERTIES("AudioLibrary.GetProperties"),
    GET_RECENTLY_ADDED_ALBUMS("AudioLibrary.GetRecentlyAddedAlbums"),
    GET_RECENTLY_ADDED_SONGS("AudioLibrary.GetRecentlyAddedSongs"),
    GET_RECENTLY_PLAYED_ALBUMS("AudioLibrary.GetRecentlyPlayedAlbums"),
    GET_RECENTLY_PLAYED_SONGS("AudioLibrary.GetRecentlyPlayedSongs"),
    GET_ROLES("AudioLibrary.GetRoles"),
    GET_SONG_DETAILS("AudioLibrary.GetSongDetails"),
    GET_SONGS("AudioLibrary.GetSongs"),
    SCAN("AudioLibrary.Scan"),
    SET_ALBUM_DETAILS("AudioLibrary.SetAlbumDetails"),
    SET_ARTIST_DETAILS("AudioLibrary.SetArtistDetails"),
    SET_SONG_DETAILS("AudioLibrary.SetSongDetails"), ;

    override fun toString(): String {
        return methodName
    }
}