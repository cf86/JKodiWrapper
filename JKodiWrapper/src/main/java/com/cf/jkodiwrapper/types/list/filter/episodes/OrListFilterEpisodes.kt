package com.cf.jkodiwrapper.types.list.filter.episodes

import com.cf.jkodiwrapper.types.list.ListFilterEpisodes

data class OrListFilterEpisodes(var or: List<ListFilterEpisodes>) : ListFilterEpisodes() {

    override fun toJSON(): String {
        return "\"or\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}