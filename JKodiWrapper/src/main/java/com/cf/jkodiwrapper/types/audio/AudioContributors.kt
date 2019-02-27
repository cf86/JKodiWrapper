package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioContributors(var artistid: Int = -1,
                             var name: String? = null,
                             var role: String? = null,
                             var roleid: Int = -1)