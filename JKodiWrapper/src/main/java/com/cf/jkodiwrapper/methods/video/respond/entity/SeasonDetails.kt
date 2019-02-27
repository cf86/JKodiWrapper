package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoDetailsSeason
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SeasonDetails @JvmOverloads constructor(var seasondetails: VideoDetailsSeason? = null)