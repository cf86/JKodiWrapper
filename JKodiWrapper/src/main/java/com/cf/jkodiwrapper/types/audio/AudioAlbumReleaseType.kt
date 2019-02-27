package com.cf.jkodiwrapper.types.audio

enum class AudioAlbumReleaseType(var type: String) {

    ALBUM("album"),
    SINGLE("single");

    override fun toString(): String {
        return type
    }
}