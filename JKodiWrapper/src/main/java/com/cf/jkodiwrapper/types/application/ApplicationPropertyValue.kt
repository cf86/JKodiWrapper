package com.cf.jkodiwrapper.types.application

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApplicationPropertyValue(var muted: Boolean = false,
                                    var name: String = "",
                                    var version: Map<String, String>? = null,
                                    var volume: Int = 0)