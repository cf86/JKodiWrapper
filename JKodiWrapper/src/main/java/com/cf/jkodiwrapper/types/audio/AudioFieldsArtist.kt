package com.cf.jkodiwrapper.types.audio

enum class AudioFieldsArtist(var field: String) {

    BORN("born"),
    COMPILATIONARTIST("compilationartist"),
    DATEADDED("dateadded"),
    DESCRIPTION("description"),
    DIED("died"),
    DISBANDED("disbanded"),
    FANART("fanart"),
    FORMED("formed"),
    GENRE("genre"),
    INSTRUMENT("instrument"),
    ISALBUMARTIST("isalbumartist"),
    MOOD("mood"),
    MUSICBRAINZARTISTID("musicbrainzartistid"),
    ROLES("roles"),
    SONGGENRES("songgenres"),
    STYLE("style"),
    THUMBNAIL("thumbnail"),
    YEARSACTIVE("yearsactive");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<AudioFieldsArtist> {
            return AudioFieldsArtist.values().asList()
        }
    }
}