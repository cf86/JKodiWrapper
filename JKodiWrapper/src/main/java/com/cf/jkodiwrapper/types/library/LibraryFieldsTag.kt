package com.cf.jkodiwrapper.types.library

enum class LibraryFieldsTag(var field: String) {

    TITLE("title");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<LibraryFieldsTag> {
            return LibraryFieldsTag.values().asList()
        }
    }
}