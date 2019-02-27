package com.cf.jkodiwrapper.types.library

import com.cf.jkodiwrapper.types.item.ItemDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LibraryDetailsGenre(var genreid: Int? = null,
                               var thumbnail: String? = null,
                               var title: String? = null) : ItemDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? LibraryDetailsGenre ?: return false
        return genreid == obj.genreid && thumbnail == obj.thumbnail && title == obj.title && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (genreid ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }

}