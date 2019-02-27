package com.cf.jkodiwrapper.types.playlist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Playlist(var playlistid: Int? = null,
                    var type: String? = null)