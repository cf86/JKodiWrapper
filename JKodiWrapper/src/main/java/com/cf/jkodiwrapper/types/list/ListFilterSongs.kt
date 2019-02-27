package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.audio.params.entity.filter.song.AbstractSongFilter
import com.cf.jkodiwrapper.types.list.filter.song.AndListFilterSongs
import com.cf.jkodiwrapper.types.list.filter.song.OrListFilterSongs
import com.cf.jkodiwrapper.types.list.filter.song.RuleListFilterSongs

abstract class ListFilterSongs : AbstractSongFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterSongs>) = OrListFilterSongs(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterSongs>) = AndListFilterSongs(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleSongs) = RuleListFilterSongs(rule)
    }
}