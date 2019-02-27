package com.cf.jkodiwrapper.types.playlist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistPosition(var pos: Int) {

    override fun toString(): String {
        return pos.toString()
    }
}