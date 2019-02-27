package com.cf.jkodiwrapper.types.list.filter.episodes

import com.cf.jkodiwrapper.types.list.ListFilterEpisodes

data class AndListFilterEpisodes(var or: List<ListFilterEpisodes>) : ListFilterEpisodes() {

    override fun toJSON(): String {
        return "\"and\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}