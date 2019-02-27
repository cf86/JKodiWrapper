package com.cf.jkodiwrapper.types.list.filter.album

import com.cf.jkodiwrapper.types.list.ListFilterAlbums

data class OrListFilterAlbums(var or: List<ListFilterAlbums>) : ListFilterAlbums() {

    override fun toJSON(): String {
        return "\"or\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}