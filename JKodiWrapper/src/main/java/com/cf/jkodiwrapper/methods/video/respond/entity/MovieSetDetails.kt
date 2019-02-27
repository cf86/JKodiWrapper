package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoDetailsMovieSetExtended
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieSetDetails @JvmOverloads constructor(var setdetails: VideoDetailsMovieSetExtended? = null)