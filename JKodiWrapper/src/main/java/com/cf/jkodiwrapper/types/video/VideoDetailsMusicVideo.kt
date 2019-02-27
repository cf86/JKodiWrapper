package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsMusicVideo(var album: String? = null,
                                  var artist: List<String> = listOf(),
                                  var genre: List<String> = listOf(),
                                  var musicvideoid: Int? = null,
                                  var premiered: String? = null,
                                  var rating: Int = 0,
                                  var studio: List<String> = listOf(),
                                  var tag: List<String> = listOf(),
                                  var track: Int = 0,
                                  var userrating: Int = 0,
                                  var year: Int = 0) : VideoDetailsFile() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsMusicVideo ?: return false
        return album == obj.album && artist == obj.artist && genre == obj.genre && musicvideoid == obj.musicvideoid &&
                premiered == obj.premiered && rating == obj.rating && studio == obj.studio && tag == obj.tag && track == obj.track &&
                userrating == obj.userrating && year == obj.year && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (album?.hashCode() ?: 0)
        result = 31 * result + artist.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + (musicvideoid ?: 0)
        result = 31 * result + (premiered?.hashCode() ?: 0)
        result = 31 * result + rating
        result = 31 * result + studio.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + track
        result = 31 * result + userrating
        result = 31 * result + year
        return result
    }
}