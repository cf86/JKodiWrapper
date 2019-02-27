package com.cf.jkodiwrapper.methods.audio.respond.entity

import com.cf.jkodiwrapper.types.audio.AudioDetailsArtist
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Artists @JvmOverloads constructor(var artists: List<AudioDetailsArtist> = listOf(),
                                             var limits: ListLimitsReturned? = null)