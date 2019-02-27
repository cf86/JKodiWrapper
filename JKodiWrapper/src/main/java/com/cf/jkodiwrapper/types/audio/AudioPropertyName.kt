package com.cf.jkodiwrapper.types.audio

enum class AudioPropertyName(var field: String) {

    MISSING_ARTIST_ID("missingartistid");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<AudioPropertyName> {
            return AudioPropertyName.values().asList()
        }
    }
}