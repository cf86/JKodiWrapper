package com.cf.jkodiwrapper.types.profiles

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProfilePassword(var encryption: String = "md5",
                           var value: String = "")