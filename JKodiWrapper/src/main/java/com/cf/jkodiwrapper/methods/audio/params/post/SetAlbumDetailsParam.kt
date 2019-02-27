package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.audio.params.entity.AlbumDetails

data class SetAlbumDetailsParam(var details: AlbumDetails) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"albumid\":${details.albumID}," +
                "\"title\":${if (details.title == null) "null" else "\"${details.title}\""}," +
                "\"artist\":${if (details.displayArtists == null) "null" else "[${details.displayArtists?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"description\":${if (details.description == null) "null" else "\"${details.description}\""}," +
                "\"genre\":${if (details.genre == null) "null" else "[${details.genre?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"theme\":${if (details.theme == null) "null" else "[${details.theme?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"mood\":${if (details.mood == null) "null" else "[${details.mood?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"style\":${if (details.style == null) "null" else "[${details.style?.joinToString(",") { "\"$it\"" }}]"}," +
                "\"type\":${if (details.type == null) "null" else "\"${details.type}\""}," +
                "\"albumlabel\":${if (details.albumLabel == null) "null" else "\"${details.albumLabel}\""}," +
                "\"rating\":${if (details.rating == null) "null" else "${details.rating}"}," +
                "\"year\":${if (details.year == null) "null" else "${details.year}"}," +
                "\"userrating\":${if (details.userrating == null) "null" else "${details.userrating}"}," +
                "\"votes\":${if (details.votes == null) "null" else "${details.votes}"}"
    }
}