package com.cf.jkodiwrapper.types.list.filter.movie

import com.cf.jkodiwrapper.types.list.ListFilterMovies
import com.cf.jkodiwrapper.types.list.ListFilterRuleMovies

class RuleListFilterMovies(var rule: ListFilterRuleMovies) : ListFilterMovies() {

    override fun toJSON(): String {
        return rule.toJSON()
    }
}