package com.cf.jkodiwrapper.types.gui

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GUIStereoscopyMode(var label: String? = null,
                              var mode: String? = null)