package com.cf.jkodiwrapper.general.error

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorData(var method: String? = null,
                     var stack: ErrorStack? = null)