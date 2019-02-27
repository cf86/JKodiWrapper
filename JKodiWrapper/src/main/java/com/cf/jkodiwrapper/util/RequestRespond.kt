package com.cf.jkodiwrapper.util

import org.apache.http.Header
import java.util.*

data class RequestRespond(var response: String, var headers: Array<Header>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestRespond

        if (response != other.response) return false
        if (!Arrays.equals(headers, other.headers)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = response.hashCode()
        result = 31 * result + Arrays.hashCode(headers)
        return result
    }
}