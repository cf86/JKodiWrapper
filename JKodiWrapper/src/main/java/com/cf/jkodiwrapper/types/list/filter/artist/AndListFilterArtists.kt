package com.cf.jkodiwrapper.types.list.filter.artist

import com.cf.jkodiwrapper.types.list.ListFilterArtists

data class AndListFilterArtists(var and: List<ListFilterArtists>) : ListFilterArtists() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}