package com.cf.jkodiwrapper.types.list.filter.artist

import com.cf.jkodiwrapper.types.list.ListFilterArtists
import com.cf.jkodiwrapper.types.list.ListFilterRuleArtists

class RuleListFilterArtists(var rule: ListFilterRuleArtists) : ListFilterArtists() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}