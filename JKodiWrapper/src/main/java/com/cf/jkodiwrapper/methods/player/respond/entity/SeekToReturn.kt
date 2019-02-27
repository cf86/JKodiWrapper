package com.cf.jkodiwrapper.methods.player.respond.entity

import com.cf.jkodiwrapper.types.global.GlobalTime
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SeekToReturn(var percentage: Double? = null,
                        var time: GlobalTime? = null,
                        var totaltime: GlobalTime? = null)