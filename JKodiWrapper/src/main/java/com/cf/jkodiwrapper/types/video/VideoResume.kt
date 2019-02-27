package com.cf.jkodiwrapper.types.video

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoResume(var position: Int = 0,
                       var total: Int = 0)