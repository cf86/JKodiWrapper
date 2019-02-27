package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class VideoDetailsMovieSet(var plot: String? = null,
                                var setid: Int? = null) : VideoDetailsMedia() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsMovieSet ?: return false
        return plot == obj.plot && setid == obj.setid && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (plot?.hashCode() ?: 0)
        result = 31 * result + (setid ?: 0)
        return result
    }
}