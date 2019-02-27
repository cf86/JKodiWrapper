package com.cf.jkodiwrapper.types.player

enum class PlayerPropertyName(var propName: String) {

    AUDIOSTREAMS("audiostreams"),
    CANCHANGESPEED("canchangespeed"),
    CANMOVE("canmove"),
    CANREPEAT("canrepeat"),
    CANROTATE("canrotate"),
    CANSEEK("canseek"),
    CANSHUFFLE("canshuffle"),
    CANZOOM("canzoom"),
    CURRENTAUDIOSTREAM("currentaudiostream"),
    CURRENTSUBTITLE("currentsubtitle"),
    CURRENTVIDEOSTREAM("currentvideostream"),
    LIVE("live"),
    PARTYMODE("partymode"),
    PERCENTAGE("percentage"),
    PLAYLISTID("playlistid"),
    POSITION("position"),
    REPEAT("repeat"),
    SHUFFLED("shuffled"),
    SPEED("speed"),
    SUBTITLEENABLED("subtitleenabled"),
    SUBTITLES("subtitles"),
    TIME("time"),
    TOTALTIME("totaltime"),
    TYPE("type"),
    VIDEOSTREAMS("videostreams");

    override fun toString(): String {
        return propName
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<PlayerPropertyName> {
            return PlayerPropertyName.values().asList()
        }
    }
}