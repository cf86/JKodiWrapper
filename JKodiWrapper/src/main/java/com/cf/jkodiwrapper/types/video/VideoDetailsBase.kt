package com.cf.jkodiwrapper.types.video

import com.cf.jkodiwrapper.types.media.MediaArtwork
import com.cf.jkodiwrapper.types.media.MediaDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class VideoDetailsBase(var art: MediaArtwork? = null,
                            var playcount: Int = 0) : MediaDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsBase ?: return false
        return art == obj.art && playcount == obj.playcount && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = art?.hashCode() ?: 0
        result = 31 * result + playcount
        return result
    }
}