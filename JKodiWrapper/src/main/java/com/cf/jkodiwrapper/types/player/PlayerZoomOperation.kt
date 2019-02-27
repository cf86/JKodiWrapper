package com.cf.jkodiwrapper.types.player

enum class PlayerZoomOperation(var op: String) {

    IN("in"),
    OUT("out");

    override fun toString(): String {
        return op
    }
}