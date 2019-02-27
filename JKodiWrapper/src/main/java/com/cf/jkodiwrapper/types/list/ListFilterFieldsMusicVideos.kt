package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsMusicVideos(var field: String) {

    ALBUM("album"),
    ARTIST("artist"),
    AUDIOCHANNELS("audiochannels"),
    AUDIOCODEC("audiocodec"),
    AUDIOCOUNT("audiocount"),
    AUDIOLANGUAGE("audiolanguage"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    FILENAME("filename"),
    GENRE("genre"),
    LASTPLAYED("lastplayed"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    PLOT("plot"),
    RATING("rating"),
    STUDIO("studio"),
    SUBTITLECOUNT("subtitlecount"),
    SUBTITLELANGUAGE("subtitlelanguage"),
    TAG("tag"),
    TIME("time"),
    TITLE("title"),
    USERRATING("userrating"),
    VIDEOASPECT("videoaspect"),
    VIDEOCODEC("videocodec"),
    VIDEORESOLUTION("videoresolution"),
    VIRTUALFOLDER("virtualfolder"),
    YEAR("year");

    override fun toString(): String {
        return field
    }
}