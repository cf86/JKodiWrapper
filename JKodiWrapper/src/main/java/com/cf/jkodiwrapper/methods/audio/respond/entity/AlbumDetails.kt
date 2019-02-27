package com.cf.jkodiwrapper.methods.audio.respond.entity

import com.cf.jkodiwrapper.types.audio.AudioDetailsAlbum
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AlbumDetails(var albumdetails: AudioDetailsAlbum? = null)