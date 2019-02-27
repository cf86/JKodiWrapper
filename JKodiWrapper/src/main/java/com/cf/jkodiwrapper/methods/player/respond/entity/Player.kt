package com.cf.jkodiwrapper.methods.player.respond.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Player(var name: String? = null,
                  var playerid: Int? = null,
                  var playsaudio: Boolean = false,
                  var playsvideo: Boolean = false,
                  var type: String? = null)