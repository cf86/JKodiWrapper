package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListLimitsReturned(var end: Int = -1,
                              var start: Int = 0,
                              var total: Int? = null)