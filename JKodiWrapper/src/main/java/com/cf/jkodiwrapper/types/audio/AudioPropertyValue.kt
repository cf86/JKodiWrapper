package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioPropertyValue(var missingartistid: Int? = null)