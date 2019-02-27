package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsEpisode
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Episodes @JvmOverloads constructor(var movies: List<VideoDetailsEpisode> = listOf(),
                                              var limits: ListLimitsReturned? = null)