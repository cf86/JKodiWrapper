package com.cf.jkodiwrapper.methods.files.respond.entity

import com.cf.jkodiwrapper.types.list.ListItemFile
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FileDetails(var filedetails: ListItemFile? = null)