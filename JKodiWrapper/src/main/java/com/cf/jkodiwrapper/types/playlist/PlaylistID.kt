package com.cf.jkodiwrapper.types.playlist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistID(var id: Int) {

    override fun toString(): String {
        return id.toString()
    }
}