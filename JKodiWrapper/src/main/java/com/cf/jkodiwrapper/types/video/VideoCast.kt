package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoCast(var name: String? = null,
                     var order: Int? = null,
                     var role: String? = null,
                     var thumbnail: String? = null)