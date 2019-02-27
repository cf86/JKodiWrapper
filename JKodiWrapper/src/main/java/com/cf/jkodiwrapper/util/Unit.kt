package com.cf.jkodiwrapper.util

import java.io.Serializable

data class Unit<T>(var value: T? = null) : Serializable {

    companion object {
        @JvmStatic
        fun getParamName(): String {
            return "value"
        }
    }
}