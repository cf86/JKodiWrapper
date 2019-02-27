package com.cf.jkodiwrapper.types.addon.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddonExtraInfo(var key: String? = null,
                          var value: String? = null)