package com.cf.jkodiwrapper.types.video.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Video(var aspect: Double = 0.0,
                 var codec: String = "",
                 var duration: Int = 0,
                 var height: Int = 0,
                 var width: Int = 0)