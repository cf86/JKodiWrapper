package com.cf.jkodiwrapper.methods.files.respond.entity

import com.cf.jkodiwrapper.types.list.ListItemsSources
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sources @JvmOverloads constructor(var limits: ListLimitsReturned? = null,
                                             var sources: List<ListItemsSources> = listOf())