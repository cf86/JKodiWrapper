package com.cf.jkodiwrapper.general.error

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorStack(var message: String? = null,
                      var name: String? = null,
                      var type: String? = null)