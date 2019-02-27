package com.cf.jkodiwrapper.methods.gui.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.gui.respond.entity.StereoscopicModes
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class StereoscopicModesRespond(var result: StereoscopicModes? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? StereoscopicModesRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}