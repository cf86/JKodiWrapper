package com.cf.jkodiwrapper.types.library

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LibraryDetailsTag(var tagid: Int? = null,
                             var title: String? = null) {

    override fun equals(other: Any?): Boolean {
        val obj = other as? LibraryDetailsTag ?: return false
        return tagid == obj.tagid && title == obj.title && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = tagid ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }
}