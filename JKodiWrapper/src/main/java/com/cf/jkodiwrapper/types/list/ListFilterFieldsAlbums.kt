package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsAlbums(var field: String) {

    ALBUM("album"),
    ALBUMARTIST("albumartist"),
    ARTIST("artist"),
    COMPILATION("compilation"),
    GENRE("genre"),
    LABEL("label"),
    LASTPLAYED("lastplayed"),
    MOODS("moods"),
    PATH("path"),
    PLAYCOUNT("playcount"),
    PLAYLIST("playlist"),
    RATING("rating"),
    REVIEW("review"),
    STYLES("styles"),
    THEMES("themes"),
    TYPE("type"),
    USERRATING("userrating"),
    VIRTUALFOLDER("virtualfolder"),
    YEAR("year");

    override fun toString(): String {
        return field
    }
}