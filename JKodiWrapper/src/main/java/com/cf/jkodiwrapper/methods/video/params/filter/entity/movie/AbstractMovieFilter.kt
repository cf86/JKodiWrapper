package com.cf.jkodiwrapper.methods.video.params.filter.entity.movie

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractMovieFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getActorFilter(actor: String) = ActorMovieFilter(actor)

        @JvmStatic
        fun getcountryFilter(country: String) = CountryMovieFilter(country)

        @JvmStatic
        fun getDirectorFilter(director: String) = DirectorMovieFilter(director)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDMovieFilter(genreID)

        @JvmStatic
        fun getGenreMovieFilter(genre: String) = GenreMovieFilter(genre)

        @JvmStatic
        fun getSetIDFilter(setID: LibraryID) = SetIDMovieFilter(setID)

        @JvmStatic
        fun getSetFilter(set: String) = SetMovieFilter(set)

        @JvmStatic
        fun getStudioFilter(studio: String) = StudioMovieFilter(studio)

        @JvmStatic
        fun getTagFilter(tag: String) = TagMovieFilter(tag)

        @JvmStatic
        fun getYearFilter(year: Int) = YearMovieFilter(year)
    }
}