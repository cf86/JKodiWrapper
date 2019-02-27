package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsEpisode(var cast: List<VideoCast> = listOf(),
                               var episode: Int? = 0,
                               var episodeid: Int? = null,
                               var firstaired: String? = null,
                               var originaltitle: String? = null,
                               var productioncode: String? = null,
                               var rating: Double = 0.0,
                               var season: Int? = 0,
                               var seasonid: Int? = null,
                               var showtitle: String? = null,
                               var specialsortepisode: Int? = null,
                               var specialsortseason: Int? = null,
                               var tvshowid: Int? = null,
                               var userrating: Int? = 0,
                               var votes: String? = null,
                               var writer: List<String> = listOf()) : VideoDetailsFile() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsEpisode ?: return false
        return cast == obj.cast && episode == obj.episode && episodeid == obj.episodeid && firstaired == obj.firstaired && originaltitle == obj.originaltitle &&
                productioncode == obj.productioncode && rating == obj.rating && season == obj.season && seasonid == obj.seasonid && showtitle == obj.showtitle &&
                specialsortepisode == obj.specialsortepisode && specialsortseason == obj.specialsortseason && tvshowid == obj.tvshowid &&
                userrating == obj.userrating && votes == obj.votes && writer == obj.writer && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + cast.hashCode()
        result = 31 * result + (episode ?: 0)
        result = 31 * result + (episodeid ?: 0)
        result = 31 * result + (firstaired?.hashCode() ?: 0)
        result = 31 * result + (originaltitle?.hashCode() ?: 0)
        result = 31 * result + (productioncode?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + (season ?: 0)
        result = 31 * result + (seasonid ?: 0)
        result = 31 * result + (showtitle?.hashCode() ?: 0)
        result = 31 * result + (specialsortepisode ?: 0)
        result = 31 * result + (specialsortseason ?: 0)
        result = 31 * result + (tvshowid ?: 0)
        result = 31 * result + (userrating ?: 0)
        result = 31 * result + (votes?.hashCode() ?: 0)
        result = 31 * result + writer.hashCode()
        return result
    }
}