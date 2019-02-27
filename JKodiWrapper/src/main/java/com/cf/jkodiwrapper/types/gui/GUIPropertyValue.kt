package com.cf.jkodiwrapper.types.gui

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GUIPropertyValue(var currentcontrol: Any? = null,
                            var currentwindow: Any? = null,
                            var fullscreen: Boolean = false,
                            var skin: Any? = null,
                            var stereoscopicmode: GUIStereoscopyMode? = null)