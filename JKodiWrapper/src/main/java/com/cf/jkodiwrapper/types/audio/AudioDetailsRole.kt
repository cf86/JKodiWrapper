package com.cf.jkodiwrapper.types.audio

import com.cf.jkodiwrapper.types.item.ItemDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioDetailsRole(var roleid: Int? = null,
                            var title: String? = null) : ItemDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsRole ?: return false
        return roleid == obj.roleid && title == obj.title && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (roleid ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }


}