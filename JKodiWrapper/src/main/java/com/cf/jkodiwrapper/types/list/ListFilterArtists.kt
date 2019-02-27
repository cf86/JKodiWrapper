package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist.AbstractArtistFilter
import com.cf.jkodiwrapper.types.list.filter.artist.AndListFilterArtists
import com.cf.jkodiwrapper.types.list.filter.artist.OrListFilterArtists
import com.cf.jkodiwrapper.types.list.filter.artist.RuleListFilterArtists

abstract class ListFilterArtists : AbstractArtistFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterArtists>) = OrListFilterArtists(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterArtists>) = AndListFilterArtists(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleArtists) = RuleListFilterArtists(rule)
    }
}