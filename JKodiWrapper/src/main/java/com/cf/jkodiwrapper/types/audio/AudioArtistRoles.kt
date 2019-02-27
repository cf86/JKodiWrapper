package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioArtistRoles(var role: String? = null,
                            var roleid: Int? = null)