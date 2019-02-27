package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos.AbstractMusicVideoFilter
import com.cf.jkodiwrapper.types.list.filter.musicvideos.AndListFilterMusicVideos
import com.cf.jkodiwrapper.types.list.filter.musicvideos.OrListFilterMusicVideos
import com.cf.jkodiwrapper.types.list.filter.musicvideos.RuleListFilterMusicVideos

abstract class ListFilterMusicVideos : AbstractMusicVideoFilter() {

    companion object {
        @JvmStatic
        fun getOrFilter(or: List<ListFilterMusicVideos>) = OrListFilterMusicVideos(or)

        @JvmStatic
        fun getAndFilter(and: List<ListFilterMusicVideos>) = AndListFilterMusicVideos(and)

        @JvmStatic
        fun getRuleFilter(rule: ListFilterRuleMusicVideos) = RuleListFilterMusicVideos(rule)
    }
}