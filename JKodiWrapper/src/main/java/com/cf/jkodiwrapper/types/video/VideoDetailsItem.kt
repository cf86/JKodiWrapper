package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class VideoDetailsItem(var dateadded: String? = null,
                            var file: String? = null,
                            var lastplayed: String? = null,
                            var plot: String? = null) : VideoDetailsMedia() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsItem ?: return false
        return dateadded == obj.dateadded && file == obj.file && lastplayed == obj.lastplayed && plot == obj.plot && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = dateadded?.hashCode() ?: 0
        result = 31 * result + (file?.hashCode() ?: 0)
        result = 31 * result + (lastplayed?.hashCode() ?: 0)
        result = 31 * result + (plot?.hashCode() ?: 0)
        return result
    }
}