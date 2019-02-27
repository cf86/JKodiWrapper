package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.audio.params.entity.SongDetails

data class SetSongDetailsParam(var details: SongDetails) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"songid\":${details.songID}," +
                "\"title\":\"${details.title}\"," +
                "\"artist\":${if (details.artist == null) "null" else "[${details.artist?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"albumartist\":${if (details.albumartist == null) "null" else "[${details.albumartist?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"genre\":${if (details.genre == null) "null" else "[${details.genre?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"year\":${details.year}," +
                "\"rating\":${details.rating}," +
                "\"album\":\"${details.album}\"," +
                "\"track\":${details.track}," +
                "\"disc\":${details.disc}," +
                "\"duration\":${details.duration}," +
                "\"comment\":\"${details.comment}\"," +
                "\"musicbrainztrackid\":\"${details.musicbrainztrackID}\"," +
                "\"musicbrainzartistid\":\"${details.musicbrainztrackID}\"," +
                "\"musicbrainzalbumid\":\"${details.musicbrainzalbumID}\"," +
                "\"musicbrainzalbumartistid\":\"${details.musicbrainzalbumID}\"," +
                "\"playcount\":${details.playcount}," +
                "\"lastplayed\":\"${details.lastplayed}\"," +
                "\"userrating\":${details.userrating}," +
                "\"votes\":${details.votes}"

    }
}