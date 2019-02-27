package com.cf.jkodiwrapper.types.list

enum class ListFieldsFiles(var fieldName: String) {

    ALBUM("album"),
    ALBUMARTIST("albumartist"),
    ALBUMARTISTID("albumartistid"),
    ALBUMID("albumid"),
    ALBUMLABEL("albumlabel"),
    ART("art"),
    ARTIST("artist"),
    ARTISTID("artistid"),
    CAST("cast"),
    COMMENT("comment"),
    COUNTRY("country"),
    DATEADDED("dateadded"),
    DESCRIPTION("description"),
    DIRECTOR("director"),
    DISC("disc"),
    DISPLAYARTIST("displayartist"),
    DURATION("duration"),
    EPISODE("episode"),
    EPISODEGUIDE("episodeguide"),
    FANART("fanart"),
    FILE("file"),
    FIRSTAIRED("firstaired"),
    GENRE("genre"),
    GENREID("genreid"),
    IMDBNUMBER("imdbnumber"),
    LASTMODIFIED("lastmodified"),
    LASTPLAYED("lastplayed"),
    LYRICS("lyrics"),
    MIMETYPE("mimetype"),
    MOOD("mood"),
    MPAA("mpaa"),
    MUSICBRAINZALBUMARTISTID("musicbrainzalbumartistid"),
    MUSICBRAINZALBUMID("musicbrainzalbumid"),
    MUSICBRAINZARTISTID("musicbrainzartistid"),
    MUSICBRAINZTRACKID("musicbrainztrackid"),
    ORIGINALTITLE("originaltitle"),
    PLAYCOUNT("playcount"),
    PLOT("plot"),
    PLOTOUTLINE("plotoutline"),
    PREMIERED("premiered"),
    PRODUCTIONCODE("productioncode"),
    RATING("rating"),
    RESUME("resume"),
    RUNTIME("runtime"),
    SEASON("season"),
    SET("set"),
    SETID("setid"),
    SHOWLINK("showlink"),
    SHOWTITLE("showtitle"),
    SIZE("size"),
    SORTTITLE("sorttitle"),
    SPECIALSORTEPISODE("specialsortepisode"),
    SPECIALSORTSEASON("specialsortseason"),
    STREAMDETAILS("streamdetails"),
    STUDIO("studio"),
    STYLE("style"),
    TAG("tag"),
    TAGLINE("tagline"),
    THEME("theme"),
    THUMBNAIL("thumbnail"),
    TITLE("title"),
    TOP250("top250"),
    TRACK("track"),
    TRAILER("trailer"),
    TVSHOWID("tvshowid"),
    UNIQUEID("uniqueid"),
    VOTES("votes"),
    WATCHEDEPISODES("watchedepisodes"),
    WRITER("writer"),
    YEAR("year");

    override fun toString(): String {
        return fieldName
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<ListFieldsFiles> {
            return ListFieldsFiles.values().asList()
        }
    }
}