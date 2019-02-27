package com.cf.jkodiwrapper.types.list.filter.musicvideos

import com.cf.jkodiwrapper.types.list.ListFilterMusicVideos
import com.cf.jkodiwrapper.types.list.ListFilterRuleMusicVideos

class RuleListFilterMusicVideos(var rule: ListFilterRuleMusicVideos) : ListFilterMusicVideos() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}