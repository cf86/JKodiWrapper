package com.cf.jkodiwrapper.methods.video.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.video.respond.entity.TvShowDetails
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TvShowDetailsRespond(var result: TvShowDetails? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? TvShowDetailsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}