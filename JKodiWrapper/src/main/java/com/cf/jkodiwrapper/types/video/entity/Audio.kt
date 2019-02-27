package com.cf.jkodiwrapper.types.video.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Audio(var channels: Int = 0,
                 var codec: String = "",
                 var language: String = "")