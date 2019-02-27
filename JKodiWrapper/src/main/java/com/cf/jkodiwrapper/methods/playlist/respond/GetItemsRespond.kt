package com.cf.jkodiwrapper.methods.playlist.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.playlist.respond.entity.PlaylistItems
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetItemsRespond(var result: PlaylistItems? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? GetItemsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}