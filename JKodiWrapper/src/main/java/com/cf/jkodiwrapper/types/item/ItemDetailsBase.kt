package com.cf.jkodiwrapper.types.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class ItemDetailsBase(var label: String? = null) {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ItemDetailsBase ?: return false
        return label == obj.label
    }

    override fun hashCode(): Int {
        return label?.hashCode() ?: 0
    }
}
