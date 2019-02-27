package com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows

import com.cf.jkodiwrapper.types.library.LibraryID

abstract class AbstractTvShowFilter {

    abstract fun toJSON(): String

    companion object {
        @JvmStatic
        fun getActorFilter(actor: String) = ActorTvShowFilter(actor)

        @JvmStatic
        fun getGenreIDFilter(genreID: LibraryID) = GenreIDTvShowFilter(genreID)

        @JvmStatic
        fun getGenreFilter(genre: String) = GenreTvShowFilter(genre)

        @JvmStatic
        fun getStudioFilter(studio: String) = StudioTvShowFilter(studio)

        @JvmStatic
        fun getTagFilter(tag: String) = TagTvShowFilter(tag)

        @JvmStatic
        fun getYearFilter(year: Int) = YearTvShowFilter(year)
    }
}