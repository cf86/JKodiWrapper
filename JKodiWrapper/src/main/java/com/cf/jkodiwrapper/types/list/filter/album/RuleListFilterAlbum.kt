package com.cf.jkodiwrapper.types.list.filter.album

import com.cf.jkodiwrapper.types.list.ListFilterAlbums
import com.cf.jkodiwrapper.types.list.ListFilterRuleAlbums

class RuleListFilterAlbum(var rule: ListFilterRuleAlbums) : ListFilterAlbums() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}