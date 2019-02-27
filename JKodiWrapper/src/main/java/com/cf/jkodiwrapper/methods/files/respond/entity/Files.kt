package com.cf.jkodiwrapper.methods.files.respond.entity

import com.cf.jkodiwrapper.types.list.ListItemFile
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Files @JvmOverloads constructor(var files: List<ListItemFile> = listOf(),
                                           var limits: ListLimitsReturned? = null)