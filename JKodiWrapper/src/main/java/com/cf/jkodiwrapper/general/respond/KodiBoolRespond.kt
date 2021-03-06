package com.cf.jkodiwrapper.general.respond

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KodiBoolRespond(var result: Boolean? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? KodiBoolRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}