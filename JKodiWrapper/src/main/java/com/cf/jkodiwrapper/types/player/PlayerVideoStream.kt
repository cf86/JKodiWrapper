package com.cf.jkodiwrapper.types.player

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerVideoStream(var codec: String? = null,
                             var height: Int? = null,
                             var index: Int? = null,
                             var language: String? = null,
                             var name: String? = null,
                             var width: Int? = null)