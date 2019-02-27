package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.audio.params.entity.filter.album.AbstractAlbumFilter
import com.cf.jkodiwrapper.types.list.filter.album.AndListFilterAlbums
import com.cf.jkodiwrapper.types.list.filter.album.OrListFilterAlbums
import com.cf.jkodiwrapper.types.list.filter.album.RuleListFilterAlbum

abstract class ListFilterAlbums : AbstractAlbumFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterAlbums>) = OrListFilterAlbums(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterAlbums>) = AndListFilterAlbums(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleAlbums) = RuleListFilterAlbum(rule)
    }
}