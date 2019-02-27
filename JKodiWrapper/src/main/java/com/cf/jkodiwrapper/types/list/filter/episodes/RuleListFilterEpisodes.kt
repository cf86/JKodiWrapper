package com.cf.jkodiwrapper.types.list.filter.episodes

import com.cf.jkodiwrapper.types.list.ListFilterEpisodes
import com.cf.jkodiwrapper.types.list.ListFilterRuleEpisodes

class RuleListFilterEpisodes(var rule: ListFilterRuleEpisodes) : ListFilterEpisodes() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}