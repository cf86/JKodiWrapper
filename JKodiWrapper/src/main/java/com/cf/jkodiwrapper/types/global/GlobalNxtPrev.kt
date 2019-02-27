package com.cf.jkodiwrapper.types.global

enum class GlobalNxtPrev(var state: String) {

    NEXT("next"),
    PREVIOUS("previous");

    override fun toString(): String {
        return state
    }
}