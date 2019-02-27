package com.cf.jkodiwrapper.types.audio

enum class AudioFieldsAlbum(var field: String) {

    ALBUMLABEL("albumlabel"),
    ARTIST("artist"),
    ARTISTID("artistid"),
    COMPILATION("compilation"),
    DATEADDED("dateadded"),
    DESCRIPTION("description"),
    DISPLAYARTIST("displayartist"),
    FANART("fanart"),
    GENRE("genre"),
    GENREID("genreid"),
    MOOD("mood"),
    MUSICBRAINZALBUMARTISTID("musicbrainzalbumartistid"),
    MUSICBRAINZALBUMID("musicbrainzalbumid"),
    PLAYCOUNT("playcount"),
    RATING("rating"),
    RELEASETYPE("releasetype"),
    STYLE("style"),
    THEME("theme"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TYPE("type"),
    USERRATING("userrating"),
    VOTES("votes"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<AudioFieldsAlbum> {
            return AudioFieldsAlbum.values().asList()
        }
    }
}