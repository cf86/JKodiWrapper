package com.cf.jkodiwrapper.types.playlist

enum class PlaylistPropertyName(var field: String) {

    TYPE("type"),
    SIZE("size");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<PlaylistPropertyName> {
            return PlaylistPropertyName.values().asList()
        }
    }
}