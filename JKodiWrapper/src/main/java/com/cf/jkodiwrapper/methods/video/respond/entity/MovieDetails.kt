package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoDetailsMovie
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDetails @JvmOverloads constructor(var moviedetails: VideoDetailsMovie? = null)