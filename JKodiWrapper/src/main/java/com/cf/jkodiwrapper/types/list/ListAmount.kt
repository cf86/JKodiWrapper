package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListAmount(var id: Int = 2000000) {

    override fun toString(): String {
        return id.toString()
    }
}