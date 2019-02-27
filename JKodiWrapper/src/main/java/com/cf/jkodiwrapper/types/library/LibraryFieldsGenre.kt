package com.cf.jkodiwrapper.types.library

enum class LibraryFieldsGenre(var field: String) {

    TITLE("title"),
    THUMBNAIL("thumbnail");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<LibraryFieldsGenre> {
            return LibraryFieldsGenre.values().asList()
        }
    }
}