package com.cf.jkodiwrapper.types.player

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerSubtitle(var index: Int? = null,
                          var language: String? = null,
                          var name: String? = null)