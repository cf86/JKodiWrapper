package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsMovie
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Movies @JvmOverloads constructor(var movies: List<VideoDetailsMovie> = listOf(),
                                            var limits: ListLimitsReturned? = null)