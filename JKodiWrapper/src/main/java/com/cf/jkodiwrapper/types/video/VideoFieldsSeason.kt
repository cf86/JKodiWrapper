package com.cf.jkodiwrapper.types.video

enum class VideoFieldsSeason(var field: String) {

    ART("art"),
    EPISODE("episode"),
    FANART("fanart"),
    PLAYCOUNT("playcount"),
    SEASON("season"),
    SHOWTITLE("showtitle"),
    THUMBNAIL("thumbnail"),
    TVSHOWID("tvshowid"),
    USERRATING("userrating"),
    WATCHEDEPISODES("watchedepisodes");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<VideoFieldsSeason> {
            return VideoFieldsSeason.values().asList()
        }
    }
}