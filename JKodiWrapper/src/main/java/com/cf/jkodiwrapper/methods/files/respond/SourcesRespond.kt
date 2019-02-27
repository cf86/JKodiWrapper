package com.cf.jkodiwrapper.methods.files.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.files.respond.entity.Sources
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SourcesRespond(var result: Sources? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? SourcesRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}