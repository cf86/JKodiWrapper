package com.cf.jkodiwrapper.types.media

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class MediaArtwork(var banner: String = "",
                        var fanart: String = "",
                        var poster: String = "",
                        var thumb: String = "")