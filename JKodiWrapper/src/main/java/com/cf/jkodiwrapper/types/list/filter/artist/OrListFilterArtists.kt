package com.cf.jkodiwrapper.types.list.filter.artist

import com.cf.jkodiwrapper.types.list.ListFilterArtists

data class OrListFilterArtists(var or: List<ListFilterArtists>) : ListFilterArtists() {

    override fun toJSON(): String {
        return "\"or\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}