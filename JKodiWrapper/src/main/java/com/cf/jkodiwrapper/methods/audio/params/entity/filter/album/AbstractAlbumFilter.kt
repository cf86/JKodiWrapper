package com.cf.jkodiwrapper.methods.audio.params.entity.filter.album

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractAlbumFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getAlbumFilter(album: String) = AlbumAlbumFilter(album)

        @JvmStatic
        fun getAlbumIDFilter(albumID: LibraryID) = AlbumIDAlbumFilter(albumID)

        @JvmStatic
        fun getArtistFilter(artist: String) = ArtistAlbumFilter(artist)

        @JvmStatic
        fun getArtistIDFilter(artistID: LibraryID) = ArtistIDAlbumFilter(artistID)

        @JvmStatic
        fun getArtistIDRoleFilter(artistID: LibraryID, role: String) = ArtistIDRoleAlbumFilter(artistID, role)

        @JvmStatic
        fun getArtistIDRoleIDFilter(artistID: LibraryID, roleID: LibraryID) = ArtistIDRoleIDAlbumFilter(artistID, roleID)

        @JvmStatic
        fun getArtistRoleFilter(artist: String, role: String) = ArtistRoleAlbumFilter(artist, role)

        @JvmStatic
        fun getArtistRoleIDFilter(artist: String, roleID: LibraryID) = ArtistRoleIDAlbumFilter(artist, roleID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreAlbumFilter(genre)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDAlbumFilter(genreID)
    }
}