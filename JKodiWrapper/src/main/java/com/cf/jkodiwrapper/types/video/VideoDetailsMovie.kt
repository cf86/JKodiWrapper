package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsMovie(var cast: List<VideoCast> = listOf(),
                             var country: List<String> = listOf(),
                             var genre: List<String> = listOf(),
                             var imdbnumber: String? = null,
                             var movieid: Int? = null,
                             var mpaa: String? = null,
                             var originaltitle: String? = null,
                             var plotoutline: String? = null,
                             var premiered: String? = null,
                             var rating: Double = 0.0,
                             var set: String? = null,
                             var setid: Int? = null,
                             var showlink: List<String> = listOf(),
                             var sorttitle: String? = null,
                             var studio: List<String> = listOf(),
                             var tag: List<String> = listOf(),
                             var tagline: String? = null,
                             var top250: Int = 0,
                             var trailer: String? = null,
                             var userrating: Int = 0,
                             var votes: String? = null,
                             var writer: List<String> = listOf(),
                             var year: Int = 0) : VideoDetailsFile() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsMovie ?: return false
        return cast == obj.cast && country == obj.country && genre == obj.genre && imdbnumber == obj.imdbnumber &&
                movieid == obj.movieid && mpaa == obj.mpaa && originaltitle == obj.originaltitle &&
                plotoutline == obj.plotoutline && premiered == obj.premiered && rating == obj.rating &&
                set == obj.set && setid == obj.setid && showlink == obj.showlink && sorttitle == obj.sorttitle &&
                studio == obj.studio && tag == obj.tag && tagline == obj.tagline && top250 == obj.top250 &&
                trailer == obj.trailer && userrating == obj.userrating && votes == obj.votes &&
                writer == obj.writer && year == obj.year && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (cast.hashCode())
        result = 31 * result + country.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + (imdbnumber?.hashCode() ?: 0)
        result = 31 * result + (movieid ?: 0)
        result = 31 * result + (mpaa?.hashCode() ?: 0)
        result = 31 * result + (originaltitle?.hashCode() ?: 0)
        result = 31 * result + (plotoutline?.hashCode() ?: 0)
        result = 31 * result + (premiered?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + (set?.hashCode() ?: 0)
        result = 31 * result + (setid ?: 0)
        result = 31 * result + showlink.hashCode()
        result = 31 * result + (sorttitle?.hashCode() ?: 0)
        result = 31 * result + studio.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (tagline?.hashCode() ?: 0)
        result = 31 * result + top250
        result = 31 * result + (trailer?.hashCode() ?: 0)
        result = 31 * result + userrating
        result = 31 * result + (votes?.hashCode() ?: 0)
        result = 31 * result + writer.hashCode()
        result = 31 * result + year
        return result
    }
}