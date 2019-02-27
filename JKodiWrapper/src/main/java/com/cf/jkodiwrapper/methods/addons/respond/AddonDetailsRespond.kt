package com.cf.jkodiwrapper.methods.addons.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.addons.respond.entity.Addon
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddonDetailsRespond(var result: Addon? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AddonDetailsRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}