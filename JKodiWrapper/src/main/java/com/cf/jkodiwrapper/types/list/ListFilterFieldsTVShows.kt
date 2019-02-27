package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsTVShows(var field: String) {

    ACTOR("actor"),
    DATEADDED("dateadded"),
    DIRECTOR("director"),
    GENRE("genre"),
    INPROGRESS("inprogress"),
    LASTPLAYED("lastplayed"),
    MPAARATING("mpaarating"),
    NUMEPISODES("numepisodes"),
    NUMWATCHED("numwatched"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    PLOT("plot"),
    RATING("rating"),
    STATUS("status"),
    STUDIO("studio"),
    TAG("tag"),
    TITLE("title"),
    USERRATING("userrating"),
    VIRTUALFOLDER("virtualfolder"),
    VOTES("votes"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<ListFilterFieldsTVShows> {
            return ListFilterFieldsTVShows.values().asList()
        }
    }
}