package com.cf.jkodiwrapper.types.addon.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddonDependency(var addonid: String? = null,
                           var optional: Boolean? = null,
                           var version: String? = null)