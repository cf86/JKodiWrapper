package com.cf.jkodiwrapper.types.audio

enum class AudioFieldsSong(var field: String) {

    ALBUM("album"),
    ALBUMARTIST("albumartist"),
    ALBUMARTISTID("albumartistid"),
    ALBUMID("albumid"),
    ALBUMRELEASETYPE("albumreleasetype"),
    ARTIST("artist"),
    ARTISTID("artistid"),
    COMMENT("comment"),
    CONTRIBUTORS("contributors"),
    DATEADDED("dateadded"),
    DISC("disc"),
    DISPLAYARTIST("displayartist"),
    DISPLAYCOMPOSER("displaycomposer"),
    DISPLAYCONDUCTOR("displayconductor"),
    DISPLAYLYRICIST("displaylyricist"),
    DISPLAYORCHESTRA("displayorchestra"),
    DURATION("duration"),
    FANART("fanart"),
    FILE("file"),
    GENRE("genre"),
    GENREID("genreid"),
    LASTPLAYED("lastplayed"),
    LYRICS("lyrics"),
    MOOD("mood"),
    MUSICBRAINZALBUMARTISTID("musicbrainzalbumartistid"),
    MUSICBRAINZALBUMID("musicbrainzalbumid"),
    MUSICBRAINZARTISTID("musicbrainzartistid"),
    MUSICBRAINZTRACKID("musicbrainztrackid"),
    PLAYCOUNT("playcount"),
    RATING("rating"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TRACK("track"),
    USERRATING("userrating"),
    VOTES("votes"),
    YEAR("year");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<AudioFieldsSong> {
            return AudioFieldsSong.values().asList()
        }
    }
}