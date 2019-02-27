package com.cf.jkodiwrapper.types.list.filter.tvshows

import com.cf.jkodiwrapper.types.list.ListFilterRuleTvShows
import com.cf.jkodiwrapper.types.list.ListFilterTvShows

class RuleListFilterTvShows(var rule: ListFilterRuleTvShows) : ListFilterTvShows() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}