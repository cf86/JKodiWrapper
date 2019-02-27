package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoDetailsTVShow
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TvShowDetails @JvmOverloads constructor(var tvshowdetails: VideoDetailsTVShow? = null)