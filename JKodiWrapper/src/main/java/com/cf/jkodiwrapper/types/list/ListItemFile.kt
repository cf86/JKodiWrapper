package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListItemFile(// var file: String? = null,
        var filetype: String? = null,
        var lastmodified: String? = null,
        var mimetype: String? = null,
        var size: Long = 0) : ListItemBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ListItemFile ?: return false
        return filetype == obj.filetype && lastmodified == obj.lastmodified && mimetype == mimetype &&
                size == size && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = filetype?.hashCode() ?: 0
        result = 31 * result + (lastmodified?.hashCode() ?: 0)
        result = 31 * result + (mimetype?.hashCode() ?: 0)
        result = 31 * result + size.hashCode()
        return result
    }
}