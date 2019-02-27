package com.cf.jkodiwrapper.types.list.filter.musicvideos

import com.cf.jkodiwrapper.types.list.ListFilterMusicVideos

data class OrListFilterMusicVideos(var and: List<ListFilterMusicVideos>) : ListFilterMusicVideos() {

    override fun toJSON(): String {
        return "\"or\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}