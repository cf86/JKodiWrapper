package com.cf.jkodiwrapper.types.video.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Subtitle(var language: String = "")