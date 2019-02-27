package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsEpisodes(var field: String) {

    ACTOR("actor"),
    AIRDATE("airdate"),
    AUDIOCHANNELS("audiochannels"),
    AUDIOCODEC("audiocodec"),
    AUDIOCOUNT("audiocount"),
    AUDIOLANGUAGE("audiolanguage"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    EPISODE("episode"),
    FILENAME("filename"),
    GENRE("genre"),
    INPROGRESS("inprogress"),
    LASTPLAYED("lastplayed"),
    MPAARATING("mpaarating"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    PLOT("plot"),
    RATING("rating"),
    SEASON("season"),
    STUDIO("studio"),
    SUBTITLECOUNT("subtitlecount"),
    SUBTITLELANGUAGE("subtitlelanguage"),
    TAG("tag"),
    TIME("time"),
    TITLE("title"),
    TVSHOW("episode"),
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

    companion object {
        @JvmStatic
        fun getAllFields(): List<ListFilterFieldsEpisodes> {
            return ListFilterFieldsEpisodes.values().asList()
        }
    }
}