package com.cf.jkodiwrapper.general.respond

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KodiStringRespond(var result: String? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? KodiStringRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}