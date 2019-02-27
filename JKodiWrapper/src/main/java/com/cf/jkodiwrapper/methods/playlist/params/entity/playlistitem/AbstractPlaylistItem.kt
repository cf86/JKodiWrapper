package com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem

import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractPlaylistItem {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getAlbumIDItem(albumID: LibraryID) = AlbumIDPLItem(albumID)

        @JvmStatic
        fun getArtistIDItem(artistID: LibraryID) = ArtistIDPLItem(artistID)

        @JvmStatic
        fun getDirectoryItem(directory: String, mediaType: FilesMedia = FilesMedia.FILES, recursive: Boolean = false) = DirectoryPLItem(directory, mediaType, recursive)

        @JvmStatic
        fun getEpisodeItem(episodeID: LibraryID) = EpisodeIDPLItem(episodeID)

        @JvmStatic
        fun getFileItem(path: String) = FilePLItem(path)

        @JvmStatic
        fun getGenreItem(genreID: LibraryID) = GenreIDPLItem(genreID)

        @JvmStatic
        fun getMovieItem(movieID: LibraryID) = MovieIDPLItem(movieID)

        @JvmStatic
        fun getMusicVideoItem(musicVideoID: LibraryID) = MusivVideoIDPLItem(musicVideoID)

        @JvmStatic
        fun getSongItem(songID: LibraryID) = SongIDPLItem(songID)
    }
}