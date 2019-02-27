package com.cf.jkodiwrapper.methods.audio.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.audio.respond.entity.Songs
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SongsRespond(var result: Songs? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? SongsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}