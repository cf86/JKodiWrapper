package com.cf.jkodiwrapper.methods.addons.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.addons.respond.entity.Addons
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddonsRespond(var result: Addons? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AddonsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}