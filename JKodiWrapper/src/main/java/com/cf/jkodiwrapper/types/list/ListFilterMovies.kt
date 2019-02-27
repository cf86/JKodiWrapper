package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter
import com.cf.jkodiwrapper.types.list.filter.movie.AndListFilterMovies
import com.cf.jkodiwrapper.types.list.filter.movie.OrListFilterMovies
import com.cf.jkodiwrapper.types.list.filter.movie.RuleListFilterMovies

abstract class ListFilterMovies : AbstractMovieFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterMovies>) = OrListFilterMovies(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterMovies>) = AndListFilterMovies(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleMovies) = RuleListFilterMovies(rule)
    }
}