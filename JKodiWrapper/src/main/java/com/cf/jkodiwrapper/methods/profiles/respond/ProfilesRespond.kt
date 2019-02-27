package com.cf.jkodiwrapper.methods.profiles.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.profiles.respond.entity.Profiles
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProfilesRespond(var result: Profiles? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ProfilesRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}