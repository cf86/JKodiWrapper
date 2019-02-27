package com.cf.jkodiwrapper.types.audio

enum class AudioFieldsRole(var field: String) {

    TITLE("title");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<AudioFieldsRole> {
            return AudioFieldsRole.values().asList()
        }
    }
}