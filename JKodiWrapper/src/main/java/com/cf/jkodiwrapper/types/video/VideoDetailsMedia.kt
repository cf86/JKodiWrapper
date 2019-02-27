package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class VideoDetailsMedia(var title: String? = null) : VideoDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsMedia ?: return false
        return title == obj.title && super.equals(other)
    }

    override fun hashCode(): Int {
        return title?.hashCode() ?: 0
    }
}