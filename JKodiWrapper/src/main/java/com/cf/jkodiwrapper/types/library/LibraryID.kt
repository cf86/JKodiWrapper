package com.cf.jkodiwrapper.types.library

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LibraryID(var id: Int = -1) {

    override fun toString(): String {
        return id.toString()
    }
}