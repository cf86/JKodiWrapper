package com.cf.jkodiwrapper.methods.player.respond.entity

import com.cf.jkodiwrapper.types.list.ListItemAll
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerItem(var item: ListItemAll? = null)