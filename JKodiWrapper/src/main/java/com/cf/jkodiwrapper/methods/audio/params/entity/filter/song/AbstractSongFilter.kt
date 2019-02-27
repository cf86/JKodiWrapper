package com.cf.jkodiwrapper.methods.audio.params.entity.filter.song

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractSongFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getAlbumIDFilter(albumID: LibraryID) = AlbumIDSongFilter(albumID)

        @JvmStatic
        fun getAlbumFilter(album: String) = AlbumSongFilter(album)

        @JvmStatic
        fun getArtistIDRoleIDFilter(artistID: LibraryID, roleID: LibraryID) = ArtistIDRoleIDSongFilter(artistID, roleID)

        @JvmStatic
        fun getArtistIDRoleFilter(artistID: LibraryID, role: String) = ArtistIDRoleSongFilter(artistID, role)

        @JvmStatic
        fun getArtistIDFilter(artistID: LibraryID) = ArtistIDSongFilter(artistID)

        @JvmStatic
        fun getArtistRoleIDFilter(artist: String, roleID: LibraryID) = ArtistRoleIDSongFilter(artist, roleID)

        @JvmStatic
        fun getArtistRoleFilter(artist: String, role: String) = ArtistRoleSongFilter(artist, role)

        @JvmStatic
        fun getArtistFilter(artist: String) = ArtistSongFilter(artist)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDSongFilter(genreID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreSongFilter(genre)
    }
}