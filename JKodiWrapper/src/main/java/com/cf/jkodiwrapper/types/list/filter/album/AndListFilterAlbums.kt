package com.cf.jkodiwrapper.types.list.filter.album

import com.cf.jkodiwrapper.types.list.ListFilterAlbums

data class AndListFilterAlbums(var and: List<ListFilterAlbums>) : ListFilterAlbums() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}