package com.cf.jkodiwrapper.types.playlist

enum class PlaylistType(var field: String) {

    UNKNOWN("unknown"),
    VIDEO("video"),
    AUDIO("audio"),
    PICTURE("picture"),
    MIXED("mixed");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<PlaylistType> {
            return PlaylistType.values().asList()
        }
    }
}