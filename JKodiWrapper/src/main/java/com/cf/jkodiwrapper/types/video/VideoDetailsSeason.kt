package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoDetailsSeason(var episode: Int = 0,
                              var season: Int? = null,
                              var seasonid: Int? = null,
                              var showtitle: String? = null,
                              var tvshowid: Int? = null,
                              var userrating: Int = 0,
                              var watchedepisodes: Int = 0) : VideoDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsSeason ?: return false
        return episode == obj.episode && season == obj.season && seasonid == obj.seasonid && showtitle == obj.showtitle &&
                tvshowid == obj.tvshowid && userrating == obj.userrating && watchedepisodes == obj.watchedepisodes && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + episode
        result = 31 * result + (season ?: 0)
        result = 31 * result + (seasonid ?: 0)
        result = 31 * result + (showtitle?.hashCode() ?: 0)
        result = 31 * result + (tvshowid ?: 0)
        result = 31 * result + userrating
        result = 31 * result + watchedepisodes
        return result
    }
}