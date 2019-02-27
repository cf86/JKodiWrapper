package com.cf.jkodiwrapper.methods.addons.respond.entity

import com.cf.jkodiwrapper.types.addon.AddonDetails
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Addons @JvmOverloads constructor(var addons: List<AddonDetails> = listOf(),
                                            var limits: ListLimitsReturned? = ListLimitsReturned())