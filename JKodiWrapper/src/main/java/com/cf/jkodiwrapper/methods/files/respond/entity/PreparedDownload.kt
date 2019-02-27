package com.cf.jkodiwrapper.methods.files.respond.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PreparedDownload @JvmOverloads constructor(var details: Any? = null,
                                                      var mode: String? = null,
                                                      var protocol: String? = null)