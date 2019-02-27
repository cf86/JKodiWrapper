package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.audio.params.entity.filter.song.AbstractSongFilter
import com.cf.jkodiwrapper.types.list.filter.episodes.AndListFilterEpisodes
import com.cf.jkodiwrapper.types.list.filter.episodes.OrListFilterEpisodes
import com.cf.jkodiwrapper.types.list.filter.episodes.RuleListFilterEpisodes

abstract class ListFilterEpisodes : AbstractSongFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterEpisodes>) = OrListFilterEpisodes(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterEpisodes>) = AndListFilterEpisodes(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleEpisodes) = RuleListFilterEpisodes(rule)
    }
}