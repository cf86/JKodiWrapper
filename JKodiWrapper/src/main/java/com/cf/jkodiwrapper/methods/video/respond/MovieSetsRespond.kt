package com.cf.jkodiwrapper.methods.video.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.video.respond.entity.MovieSets
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieSetsRespond(var result: MovieSets? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? MovieSetsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}