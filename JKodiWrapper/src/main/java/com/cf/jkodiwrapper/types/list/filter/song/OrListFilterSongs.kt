package com.cf.jkodiwrapper.types.list.filter.song

import com.cf.jkodiwrapper.types.list.ListFilterSongs

data class OrListFilterSongs(var or: List<ListFilterSongs>) : ListFilterSongs() {

    override fun toJSON(): String {
        return "\"or\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}