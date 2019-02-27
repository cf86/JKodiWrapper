package com.cf.jkodiwrapper.types.player

enum class PlayerForwBackw(var directon: String) {

    SMALL_FORWARD("smallforward"),
    SMALL_BACKWARD("smallbackward"),
    BIG_FORWARD("bigforward"),
    BIG_BACKWARD("bigbackward");

    override fun toString(): String {
        return directon
    }
}