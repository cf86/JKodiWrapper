package com.cf.jkodiwrapper.types.video

enum class VideoFieldsMovie(var field: String) {

    ART("art"),
    CAST("cast"),
    COUNTRY("country"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    FANART("fanart"),
    FILE("file"),
    GENRE("genre"),
    IMDBNUMBER("imdbnumber"),
    LASTPLAYED("lastplayed"),
    MPAA("mpaa"),
    ORIGINALTITLE("originaltitle"),
    PLAYCOUNT("playcount"),
    PLOT("plot"),
    PLOTOUTLINE("plotoutline"),
    PREMIERED("premiered"),
    RATING("rating"),
    RATINGS("ratings"),
    RESUME("resume"),
    RUNTIME("runtime"),
    SET("set"),
    SETID("setid"),
    SHOWLINK("showlink"),
    SORTTITLE("sorttitle"),
    STREAMDETAILS("streamdetails"),
    STUDIO("studio"),
    TAG("tag"),
    TAGLINE("tagline"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TOP250("top250"),
    TRAILER("trailer"),
    UNIQUEID("uniqueid"),
    USERRATING("userrating"),
    VOTES("votes"),
    WRITER("writer"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsMovie> {
            return VideoFieldsMovie.values().asList()
        }
    }
}