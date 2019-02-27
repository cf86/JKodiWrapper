package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsMovies(var field: String) {

    ACTOR("actor"),
    AUDIOCHANNELS("audiochannels"),
    AUDIOCODEC("audiocodec"),
    AUDIOCOUNT("audiocount"),
    AUDIOLANGUAGE("audiolanguage"),
    COUNTRY("country"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    FILENAME("filename"),
    GENRE("genre"),
    HASTRAILER("hastrailer"),
    INPROGRESS("inprogress"),
    LASTPLAYED("lastplayed"),
    MPAARATING("mpaarating"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    PLOT("plot"),
    PLOTOUTLINE("plotoutline"),
    RATING("rating"),
    SET("set"),
    STUDIO("studio"),
    SUBTITLECOUNT("subtitlecount"),
    SUBTITLELANGUAGE("subtitlelanguage"),
    TAG("tag"),
    TAGLINE("tagline"),
    TIME("time"),
    TITLE("title"),
    TOP250("top250"),
    USERRATING("userrating"),
    VIDEOASPECT("videoaspect"),
    VIDEOCODEC("videocodec"),
    VIDEORESOLUTION("videoresolution"),
    VIRTUALFOLDER("virtualfolder"),
    VOTES("votes"),
    WRITERS("writers"),
    YEAR("year");

    override fun toString(): String {
        return field
    }
}