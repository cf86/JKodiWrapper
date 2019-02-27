package com.cf.jkodiwrapper.types.video

enum class VideoFieldsEpisode(var field: String) {

    ART("art"),
    CAST("cast"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    EPISODE("episode"),
    FANART("fanart"),
    FILE("file"),
    FIRSTAIRED("firstaired"),
    LASTPLAYED("lastplayed"),
    ORIGINALTITLE("originaltitle"),
    PLAYCOUNT("playcount"),
    PLOT("plot"),
    PRODUCTIONCODE("productioncode"),
    RATING("rating"),
    RATINGS("ratings"),
    RESUME("resume"),
    RUNTIME("runtime"),
    SEASON("season"),
    SEASONID("seasonid"),
    SHOWTITLE("showtitle"),
    SPECIALSORTEPISODE("specialsortepisode"),
    SPECIALSORTSEASON("specialsortseason"),
    STREAMDETAILS("streamdetails"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TVSHOWID("tvshowid"),
    UNIQUEID("uniqueid"),
    USERRATING("userrating"),
    VOTES("votes"),
    WRITER("writer");


    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsEpisode> {
            return VideoFieldsEpisode.values().asList()
        }
    }
}