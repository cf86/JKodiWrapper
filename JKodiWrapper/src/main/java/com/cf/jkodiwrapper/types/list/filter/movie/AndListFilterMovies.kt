package com.cf.jkodiwrapper.types.list.filter.movie

import com.cf.jkodiwrapper.types.list.ListFilterMovies

data class AndListFilterMovies(var and: List<ListFilterMovies>) : ListFilterMovies() {

    override fun toJSON(): String {
        return "\"and\":[{${and.joinToString(",") { it.toJSON() }}}]"
    }
}