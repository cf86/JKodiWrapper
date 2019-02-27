package com.cf.jkodiwrapper.types.list.filter.song

import com.cf.jkodiwrapper.types.list.ListFilterSongs

data class AndListFilterSongs(var and: List<ListFilterSongs>) : ListFilterSongs() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}