package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsSongs(var field: String) {

    ALBUM("album"),
    ALBUMARTIST("albumartist"),
    ARTIST("artist"),
    COMMENT("comment"),
    FILENAME("filename"),
    GENRE("genre"),
    LASTPLAYED("lastplayed"),
    MOODS("moods"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    RATING("rating"),
    TIME("time"),
    TITLE("title"),
    TRACKNUMBER("tracknumber"),
    USERRATING("userrating"),
    VIRTUALFOLDER("virtualfolder"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<ListFilterFieldsSongs> {
            return ListFilterFieldsSongs.values().asList()
        }
    }
}