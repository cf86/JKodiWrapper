package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsMovieSet
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieSets @JvmOverloads constructor(var sets: List<VideoDetailsMovieSet> = listOf(),
                                               var limits: ListLimitsReturned? = null)