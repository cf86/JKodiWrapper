package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.video.VideoDetailsEpisode
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class EpisodeDetails @JvmOverloads constructor(var episodedetails: VideoDetailsEpisode? = null)