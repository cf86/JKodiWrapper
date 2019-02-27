package com.cf.jkodiwrapper.types.video

enum class VideoFieldsMusicVideo(var field: String) {

    ALBUM("album"),
    ART("art"),
    ARTIST("artist"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    FANART("fanart"),
    FILE("file"),
    GENRE("genre"),
    LASTPLAYED("lastplayed"),
    PLAYCOUNT("playcount"),
    PLOT("plot"),
    PREMIERED("premiered"),
    RATING("rating"),
    RESUME("resume"),
    RUNTIME("runtime"),
    STREAMDETAILS("streamdetails"),
    STUDIO("studio"),
    TAG("tag"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TRACK("track"),
    USERRATING("userrating"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsMusicVideo> {
            return VideoFieldsMusicVideo.values().asList()
        }
    }
}