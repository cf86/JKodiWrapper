package com.cf.jkodiwrapper.types.list.filter.tvshows

import com.cf.jkodiwrapper.types.list.ListFilterTvShows

data class AndListFilterTvShows(var and: List<ListFilterTvShows>) : ListFilterTvShows() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}