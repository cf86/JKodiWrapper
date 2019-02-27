package com.cf.jkodiwrapper.methods.playlist.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.types.playlist.Playlist
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetPlaylistsRespond(var result: List<Playlist>? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? GetPlaylistsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}