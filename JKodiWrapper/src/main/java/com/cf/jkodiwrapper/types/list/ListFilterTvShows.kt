package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows.AbstractTvShowFilter
import com.cf.jkodiwrapper.types.list.filter.tvshows.AndListFilterTvShows
import com.cf.jkodiwrapper.types.list.filter.tvshows.OrListFilterTvShows
import com.cf.jkodiwrapper.types.list.filter.tvshows.RuleListFilterTvShows

abstract class ListFilterTvShows : AbstractTvShowFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterTvShows>) = OrListFilterTvShows(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterTvShows>) = AndListFilterTvShows(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleTvShows) = RuleListFilterTvShows(rule)
    }
}