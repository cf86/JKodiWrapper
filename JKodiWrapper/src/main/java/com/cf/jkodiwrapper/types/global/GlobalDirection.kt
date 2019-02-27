package com.cf.jkodiwrapper.types.global

enum class GlobalDirection(var direction: String) {

    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down");

    override fun toString(): String {
        return direction
    }
}