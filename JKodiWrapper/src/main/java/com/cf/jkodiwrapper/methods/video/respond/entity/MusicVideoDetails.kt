package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoFieldsMusicVideo
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MusicVideoDetails @JvmOverloads constructor(var musicvideodetails: VideoFieldsMusicVideo? = null)