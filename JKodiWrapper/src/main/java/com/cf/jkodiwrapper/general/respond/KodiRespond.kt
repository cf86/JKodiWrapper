package com.cf.jkodiwrapper.general.respond

import com.cf.jkodiwrapper.general.error.KodiError
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class KodiRespond(var id: Int? = null,
                           var jsonrpc: String? = "2.0",
                           var error: KodiError? = null) {

    fun isErrorRespond(): Boolean {
        return error != null
    }

    override fun equals(other: Any?): Boolean {
        val obj = other as? KodiRespond ?: return false
        return this.id == obj.id && obj.jsonrpc == jsonrpc && obj.error == error
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (jsonrpc?.hashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}