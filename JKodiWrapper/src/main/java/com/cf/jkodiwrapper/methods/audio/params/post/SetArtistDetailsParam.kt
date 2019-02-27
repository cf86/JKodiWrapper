package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.audio.params.entity.ArtistDetails

data class SetArtistDetailsParam(var details: ArtistDetails) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"artistid\":${details.artistID}," +
                "\"artist\":\"${details.artist}\"," +
                "\"instrument\":${if (details.instrument == null) "null" else "[${details.instrument?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"style\":${if (details.style == null) "null" else "[${details.style?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"mood\":${if (details.mood == null) "null" else "[${details.mood?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"born\":\"${details.born}\"," +
                "\"formed\":\"${details.formed}\"," +
                "\"description\":\"${details.description}\"," +
                "\"genre\":${if (details.genre == null) "null" else "[${details.genre?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"died\":\"${details.died}\"," +
                "\"disbanded\":\"${details.disbanded}\"," +
                "\"yearsactive\":${if (details.yearsactive == null) "null" else "[${details.yearsactive?.joinToString(",") { "\"$it\"" }}]"}"
    }
}