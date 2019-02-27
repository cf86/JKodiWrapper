package com.cf.jkodiwrapper.types.video

enum class VideoFieldsMovieSet(var field: String) {

    TITLE("title"),
    PLAY_COUNT("playcount"),
    FANART("fanart"),
    THUMBNAIL("thumbnail"),
    ART("art"),
    PLOT("plot");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsMovieSet> {
            return VideoFieldsMovieSet.values().asList()
        }
    }
}