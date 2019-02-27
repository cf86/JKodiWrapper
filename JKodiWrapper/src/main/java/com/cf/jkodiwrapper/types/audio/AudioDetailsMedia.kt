package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class AudioDetailsMedia(var artist: List<String> = listOf(),
                             var artistid: List<Int> = listOf(),
                             var displayartist: String? = null,
                             var genreid: List<Int> = listOf(),
                             var musicbrainzalbumartistid: List<String> = listOf(),
                             var musicbrainzalbumid: String? = null,
                             var rating: Double = 0.0,
                             var title: String? = null,
                             var userrating: Int = 0,
                             var votes: Int = 0,
                             var year: Int = 0) : AudioDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsMedia ?: return false
        return artist == obj.artist && artistid == obj.artistid && displayartist == obj.displayartist && genreid == obj.genreid
                && musicbrainzalbumartistid == obj.musicbrainzalbumartistid && musicbrainzalbumid == obj.musicbrainzalbumid
                && rating == obj.rating && title == obj.title && userrating == obj.userrating && votes == obj.votes && year == obj.year && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + artist.hashCode()
        result = 31 * result + artistid.hashCode()
        result = 31 * result + (displayartist?.hashCode() ?: 0)
        result = 31 * result + genreid.hashCode()
        result = 31 * result + (musicbrainzalbumartistid.hashCode())
        result = 31 * result + (musicbrainzalbumid?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + userrating
        result = 31 * result + votes
        result = 31 * result + year
        return result
    }
}