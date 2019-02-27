package com.cf.jkodiwrapper.general.error

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class KodiError(var code: Int? = null,
                     var data: ErrorData? = null,
                     var message: String? = null)