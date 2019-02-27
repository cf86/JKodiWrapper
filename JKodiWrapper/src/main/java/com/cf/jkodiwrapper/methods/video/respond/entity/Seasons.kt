package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsSeason
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Seasons @JvmOverloads constructor(var seasons: List<VideoDetailsSeason> = listOf(),
                                             var limits: ListLimitsReturned? = null)