package com.cf.jkodiwrapper.types.global

enum class GlobalToggle(var param: String) {

    TOGGLE("toggle");

    override fun toString(): String {
        return param
    }
}