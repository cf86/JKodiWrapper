package com.cf.jkodiwrapper.types.list.filter.musicvideos

import com.cf.jkodiwrapper.types.list.ListFilterMusicVideos

data class AndListFilterMusicVideos(var and: List<ListFilterMusicVideos>) : ListFilterMusicVideos() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}