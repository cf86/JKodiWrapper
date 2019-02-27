package com.cf.jkodiwrapper.types.player

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerAudioStream(var bitrate: Int? = null,
                             var channels: Int? = null,
                             var codec: String? = null,
                             var index: Int? = null,
                             var language: String? = null,
                             var name: String? = null)