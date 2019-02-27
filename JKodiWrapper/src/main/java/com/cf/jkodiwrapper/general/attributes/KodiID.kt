package com.cf.jkodiwrapper.general.attributes

data class KodiID(var id: Int) {

    override fun toString(): String {
        return id.toString()
    }
}