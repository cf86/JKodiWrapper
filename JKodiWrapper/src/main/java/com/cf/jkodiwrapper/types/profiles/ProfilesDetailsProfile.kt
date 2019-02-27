package com.cf.jkodiwrapper.types.profiles

import com.cf.jkodiwrapper.types.item.ItemDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProfilesDetailsProfile(var lockmode: Int = 0,
                                  var thumbnail: String? = null) : ItemDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ProfilesDetailsProfile ?: return false
        return label == obj.label
    }

    override fun hashCode(): Int {
        return label?.hashCode() ?: 0
    }
}