package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractMusicVideoFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getArtistFilter(artist: String) = ArtistMusicVideoFilter(artist)

        @JvmStatic
        fun getDirectorFilter(director: String) = DirectorMusicVideoFilter(director)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDMusicVideoFilter(genreID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreMusicVideoFilter(genre)

        @JvmStatic
        fun getStudioFilter(studio: String) = StudioMusicVideoFilter(studio)

        @JvmStatic
        fun getTagFilter(tag: String) = TagMusicVideoFilter(tag)

        @JvmStatic
        fun getYearFilter(year: Int) = YearMusicVideoFilter(year)
    }
}