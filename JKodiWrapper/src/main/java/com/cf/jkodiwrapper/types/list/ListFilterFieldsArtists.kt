package com.cf.jkodiwrapper.types.list

enum class ListFilterFieldsArtists(var field: String) {

    ARTIST("artist"),
    BANDFORMED("bandformed"),
    BIOGRAPHY("biography"),
    BORN("born"),
    DIED("died"),
    DISBANDED("disbanded"),
    GENRE("genre"),
    INSTRUMENTS("instruments"),
    MOODS("moods"),
    PATH("path"),
    PLAYLIST("playlist"),
    ROLE("role"),
    STYLES("styles"),
    VIRTUALFOLDER("virtualfolder");

    override fun toString(): String {
        return field
    }
}