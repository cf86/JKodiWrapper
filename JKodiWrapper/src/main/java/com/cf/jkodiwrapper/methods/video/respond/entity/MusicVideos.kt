package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.video.VideoDetailsMusicVideo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MusicVideos @JvmOverloads constructor(var musicvideos: List<VideoDetailsMusicVideo> = listOf(),
                                                 var limits: ListLimitsReturned? = null)