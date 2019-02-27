package com.cf.jkodiwrapper.methods.audio.respond.entity

import com.cf.jkodiwrapper.types.audio.AudioDetailsAlbum
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Albums @JvmOverloads constructor(var albums: List<AudioDetailsAlbum> = listOf(),
                                            var limits: ListLimitsReturned? = null)