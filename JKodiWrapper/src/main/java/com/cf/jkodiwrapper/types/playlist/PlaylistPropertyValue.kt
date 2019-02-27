package com.cf.jkodiwrapper.types.playlist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistPropertyValue(var size: Int = 0,
                                 var type: String = "")