package com.cf.jkodiwrapper.types.list.filter.song

import com.cf.jkodiwrapper.types.list.ListFilterRuleSongs
import com.cf.jkodiwrapper.types.list.ListFilterSongs

class RuleListFilterSongs(var rule: ListFilterRuleSongs) : ListFilterSongs() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}