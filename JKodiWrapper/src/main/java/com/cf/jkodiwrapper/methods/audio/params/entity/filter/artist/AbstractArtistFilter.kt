package com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractArtistFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getAlbumFilter(album: String) = AlbumArtistFilter(album)

        @JvmStatic
        fun getAlbumIDFilter(albumID: LibraryID) = AlbumIDArtistFilter(albumID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreArtistFilter(genre)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDArtistFilter(genreID)

        @JvmStatic
        fun getRoleFilter(role: String) = RoleArtistFilter(role)

        @JvmStatic
        fun getRoleIDFilter(roleID: LibraryID) = RoleIDArtistFilter(roleID)

        @JvmStatic
        fun getRoleIDSongGenreFilter(roleID: LibraryID, songGenre: String) = RoleIDSongGenreArtistFilter(roleID, songGenre)

        @JvmStatic
        fun getRoleIDSongGenreIDFilter(roleID: LibraryID, songGenreID: LibraryID) = RoleIDSongGenreIDArtistFilter(roleID, songGenreID)

        @JvmStatic
        fun getRoleIDSongIDFilter(roleID: LibraryID, songID: LibraryID) = RoleIDSongIDArtistFilter(roleID, songID)

        @JvmStatic
        fun getRoleSongGenreFilter(role: String, songGenre: String) = RoleSongGenreArtistFilter(role, songGenre)

        @JvmStatic
        fun getRoleSongGenreIDFilter(role: String, songGenreID: LibraryID) = RoleSongGenreIDArtistFilter(role, songGenreID)

        @JvmStatic
        fun getRoleSongIDFilter(role: String, songID: LibraryID) = RoleSongIDArtistFilter(role, songID)

        @JvmStatic
        fun getSongGenreFilter(songGenre: String) = SongGenreArtistFilter(songGenre)

        @JvmStatic
        fun getSongGenreIDFilter(songGenreID: LibraryID) = SongGenreIDArtistFilter(songGenreID)

        @JvmStatic
        fun getSongIDFilter(songID: LibraryID) = SongIDArtistFilter(songID)
    }
}