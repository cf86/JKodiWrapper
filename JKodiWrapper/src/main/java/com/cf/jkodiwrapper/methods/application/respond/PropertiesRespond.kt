package com.cf.jkodiwrapper.methods.application.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.types.application.ApplicationPropertyValue
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PropertiesRespond(var result: ApplicationPropertyValue? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? PropertiesRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}