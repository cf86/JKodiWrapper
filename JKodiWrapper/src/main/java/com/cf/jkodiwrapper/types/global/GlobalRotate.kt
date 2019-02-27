package com.cf.jkodiwrapper.types.global

enum class GlobalRotate(var direction: String) {

    CLOCKWISE("clockwise"),
    COUNTER_CLOCKWISE("counterclockwise");

    override fun toString(): String {
        return direction
    }
}