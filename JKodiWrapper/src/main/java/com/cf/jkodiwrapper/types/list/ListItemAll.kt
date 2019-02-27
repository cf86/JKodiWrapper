package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListItemAll(var channel: String? = null,
                       var channelnumber: Int = 0,
                       var channeltype: String = "tv",
                       var endtime: String? = null,
                       var hidden: Boolean = false,
                       var locked: Boolean = false,
                       var starttime: String? = null) : ListItemBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ListItemAll ?: return false
        return channel == obj.channel && channelnumber == obj.channelnumber && channeltype == obj.channeltype && endtime == obj.endtime
                && hidden == obj.hidden && locked == obj.locked && starttime == obj.starttime && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (channel?.hashCode() ?: 0)
        result = 31 * result + channelnumber
        result = 31 * result + channeltype.hashCode()
        result = 31 * result + (endtime?.hashCode() ?: 0)
        result = 31 * result + hidden.hashCode()
        result = 31 * result + locked.hashCode()
        result = 31 * result + (starttime?.hashCode() ?: 0)
        return result
    }

}