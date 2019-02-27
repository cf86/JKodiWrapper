package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsTVShow
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class InProgressTvShows @JvmOverloads constructor(var tvshows: List<VideoDetailsTVShow> = listOf(),
                                                       var limits: ListLimitsReturned? = null)