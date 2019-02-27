package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsTVShow(var cast: List<VideoCast> = listOf(),
                              var episode: Int = 0,
                              var episodeguide: String? = null,
                              var genre: List<String> = listOf(),
                              var imdbnumber: String? = null,
                              var mpaa: String? = null,
                              var originaltitle: String? = null,
                              var premiered: String? = null,
                              var rating: Double = 0.0,
                              var runtime: Int = 0,
                              var season: Int = 0,
                              var sorttitle: String? = null,
                              var studio: List<String> = listOf(),
                              var tag: List<String> = listOf(),
                              var tvshowid: Int? = null,
                              var userrating: Int = 0,
                              var votes: String? = null,
                              var watchedepisodes: Int? = null,
                              var year: Int = 0) : VideoDetailsItem() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsTVShow ?: return false
        return cast == obj.cast && episode == obj.episode && episodeguide == obj.episodeguide && genre == obj.genre &&
                imdbnumber == obj.imdbnumber && mpaa == obj.mpaa && originaltitle == obj.originaltitle && premiered == obj.premiered &&
                rating == obj.rating && runtime == obj.runtime && season == obj.season && sorttitle == obj.sorttitle &&
                studio == obj.studio && tag == obj.tag && tvshowid == obj.tvshowid && userrating == obj.userrating &&
                votes == obj.votes && watchedepisodes == obj.watchedepisodes && year == obj.year && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + cast.hashCode()
        result = 31 * result + episode
        result = 31 * result + (episodeguide?.hashCode() ?: 0)
        result = 31 * result + genre.hashCode()
        result = 31 * result + (imdbnumber?.hashCode() ?: 0)
        result = 31 * result + (mpaa?.hashCode() ?: 0)
        result = 31 * result + (originaltitle?.hashCode() ?: 0)
        result = 31 * result + (premiered?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + runtime
        result = 31 * result + season
        result = 31 * result + (sorttitle?.hashCode() ?: 0)
        result = 31 * result + studio.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (tvshowid ?: 0)
        result = 31 * result + userrating
        result = 31 * result + (votes?.hashCode() ?: 0)
        result = 31 * result + (watchedepisodes ?: 0)
        result = 31 * result + year
        return result
    }
}