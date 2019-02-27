package com.cf.jkodiwrapper.types.video

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsMovieSetExtended(var limits: ListLimitsReturned? = null,
                                        var movies: List<VideoDetailsMovie> = listOf()) : VideoDetailsMovieSet() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsMovieSetExtended ?: return false
        return limits == obj.limits && movies == obj.movies && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (limits?.hashCode() ?: 0)
        result = 31 * result + movies.hashCode()
        return result
    }
}