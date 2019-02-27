package com.cf.jkodiwrapper.types.list.filter.tvshows

import com.cf.jkodiwrapper.types.list.ListFilterTvShows

data class OrListFilterTvShows(var and: List<ListFilterTvShows>) : ListFilterTvShows() {

    override fun toJSON(): String {
        return "\"or\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}