package com.cf.jkodiwrapper.methods.audio.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.audio.respond.entity.SongDetails
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class SongDetailsRespond(var result: SongDetails) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? SongDetailsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}