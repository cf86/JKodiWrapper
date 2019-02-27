package com.cf.jkodiwrapper.methods.playlist.respond.entity

import com.cf.jkodiwrapper.types.list.ListItemAll
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistItems(var items: List<ListItemAll> = listOf(),
                         var limits: ListLimitsReturned? = null)