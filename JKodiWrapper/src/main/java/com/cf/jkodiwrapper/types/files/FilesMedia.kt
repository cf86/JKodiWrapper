package com.cf.jkodiwrapper.types.files

enum class FilesMedia(var media: String) {

    VIDEO("video"),
    MUSIC("music"),
    PICTURES("pictures"),
    FILES("files"),
    PROGRAMS("programs");

    override fun toString(): String {
        return media
    }
}