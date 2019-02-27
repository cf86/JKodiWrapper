package com.cf.jkodiwrapper.methods.audio.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.audio.respond.entity.ArtistDetails
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ArtistRespond(var result: ArtistDetails? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ArtistRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}