package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoFieldsTVShow
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TvShows @JvmOverloads constructor(var tvshows: List<VideoFieldsTVShow> = listOf(),
                                             var limits: ListLimitsReturned? = null)