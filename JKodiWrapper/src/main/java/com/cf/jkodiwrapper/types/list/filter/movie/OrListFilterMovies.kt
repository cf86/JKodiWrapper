package com.cf.jkodiwrapper.types.list.filter.movie

import com.cf.jkodiwrapper.types.list.ListFilterMovies

data class OrListFilterMovies(var or: List<ListFilterMovies>) : ListFilterMovies() {

    override fun toJSON(): String {
        return "\"or\":[{${or.joinToString(",") { it.toJSON() }}}]"
    }
}