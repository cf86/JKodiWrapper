package com.cf.jkodiwrapper.methods.gui.respond.entity

import com.cf.jkodiwrapper.types.gui.GUIStereoscopyMode
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class StereoscopicModes(var stereoscopicmodes: List<GUIStereoscopyMode>)