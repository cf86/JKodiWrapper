package com.cf.jkodiwrapper.methods.audio.respond.entity

import com.cf.jkodiwrapper.types.audio.AudioDetailsRole
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Roles @JvmOverloads constructor(var roles: List<AudioDetailsRole> = listOf(),
                                           var limits: ListLimitsReturned? = null)