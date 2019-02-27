package com.cf.jkodiwrapper.types.audio

import com.cf.jkodiwrapper.types.media.MediaDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class AudioDetailsBase(var dateadded: String? = null,
                            var genre: List<String> = listOf()) : MediaDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsBase ?: return false
        return dateadded == obj.dateadded && genre == obj.genre && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (dateadded?.hashCode() ?: 0)
        result = 31 * result + genre.hashCode()
        return result
    }
}