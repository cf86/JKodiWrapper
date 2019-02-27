package com.cf.jkodiwrapper.types.player

data class PlayerID(var id: Int) {

    override fun toString(): String {
        return id.toString()
    }
}