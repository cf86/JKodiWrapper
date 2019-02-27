package com.cf.jkodiwrapper.types.video

enum class VideoFieldsTVShow(var field: String) {

    ART("art"),
    CAST("cast"),
    DATEADDED("dateadded"),
    EPISODE("episode"),
    EPISODEGUIDE("episodeguide"),
    FANART("fanart"),
    FILE("file"),
    GENRE("genre"),
    IMDBNUMBER("imdbnumber"),
    LASTPLAYED("lastplayed"),
    MPAA("mpaa"),
    ORIGINALTITLE("originaltitle"),
    PLAYCOUNT("playcount"),
    PLOT("plot"),
    PREMIERED("premiered"),
    RATING("rating"),
    RATINGS("ratings"),
    RUNTIME("runtime"),
    SEASON("season"),
    SORTTITLE("sorttitle"),
    STUDIO("studio"),
    TAG("tag"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    UNIQUEID("uniqueid"),
    USERRATING("userrating"),
    VOTES("votes"),
    WATCHEDEPISODES("watchedepisodes"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsTVShow> {
            return VideoFieldsTVShow.values().asList()
        }
    }
}