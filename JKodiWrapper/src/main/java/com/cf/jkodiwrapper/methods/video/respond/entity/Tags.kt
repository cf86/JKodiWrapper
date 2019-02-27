package com.cf.jkodiwrapper.methods.video.respond.entity

import com.cf.jkodiwrapper.types.library.LibraryDetailsTag
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tags @JvmOverloads constructor(var tags: List<LibraryDetailsTag> = listOf(),
                                          var limits: ListLimitsReturned? = null)