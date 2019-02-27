package com.cf.jkodiwrapper.types.media

import com.cf.jkodiwrapper.types.item.ItemDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class MediaDetailsBase(var fanart: String? = null,
                            var thumbnail: String? = null) : ItemDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? MediaDetailsBase ?: return false
        return fanart == obj.fanart && thumbnail == obj.thumbnail && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = fanart?.hashCode() ?: 0
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        return result
    }
}