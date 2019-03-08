package com.cf.jkodiwrapper.types.subtitle

enum class SubtitleOperation(var op: String) {

    PREVIOUS("previous"),
    NEXT("next"),
    OFF("off"),
    ON("on");

    override fun toString(): String {
        return op
    }
}