package com.cf.jkodiwrapper.types.player

enum class PlayerType(var type: String) {

    VIDEO("video"),
    AUDIO("audio"),
    PICTURE("picture");

    override fun toString(): String {
        return type
    }
}