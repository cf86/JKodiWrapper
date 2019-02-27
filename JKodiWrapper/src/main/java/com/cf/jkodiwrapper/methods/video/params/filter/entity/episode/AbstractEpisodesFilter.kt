package com.cf.jkodiwrapper.methods.video.params.filter.entity.episode

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractEpisodesFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getActorFilter(actor: String) = ActorEpisodesFilter(actor)

        @JvmStatic
        fun getDirectorFilter(director: String) = DirectorEpisodesFilter(director)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDEpisodesFilter(genreID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreEpisodesFilter(genre)

        @JvmStatic
        fun getYearFilter(year: Int) = YearEpisodesFilter(year)
    }
}