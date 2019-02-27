package com.cf.jkodiwrapper.methods.addons.respond.entity

import com.cf.jkodiwrapper.types.addon.AddonDetails
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Addon(var addon: AddonDetails? = null)