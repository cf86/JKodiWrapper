package com.cf.jkodiwrapper.methods.profiles.respond.entity

import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.profiles.ProfilesDetailsProfile

data class Profiles @JvmOverloads constructor(var profiles: List<ProfilesDetailsProfile> = listOf(),
                                              var limits: ListLimitsReturned? = null)