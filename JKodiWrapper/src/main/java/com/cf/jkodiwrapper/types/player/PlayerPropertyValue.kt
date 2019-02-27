package com.cf.jkodiwrapper.types.player

import com.cf.jkodiwrapper.types.global.GlobalTime
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlayerPropertyValue(var audiostreams: List<PlayerAudioStream> = listOf(),
                               var canchangespeed: Boolean = false,
                               var canmove: Boolean = false,
                               var canrepeat: Boolean = false,
                               var canrotate: Boolean = false,
                               var canseek: Boolean = false,
                               var canshuffle: Boolean = false,
                               var canzoom: Boolean = false,
                               var currentaudiostream: PlayerAudioStream? = null,
                               var currentsubtitle: PlayerSubtitle? = null,
                               var currentvideostream: PlayerVideoStream? = null,
                               var live: Boolean = false,
                               var partymode: Boolean = false,
                               var percentage: Double = 0.0,
                               var playlistid: Int = -1,
                               var position: Int = -1,
                               var repeat: String = "off",
                               var shuffled: Boolean = false,
                               var speed: Int = 0,
                               var subtitleenabled: Boolean = false,
                               var subtitles: List<PlayerSubtitle> = listOf(),
                               var time: GlobalTime? = null,
                               var totaltime: GlobalTime? = null,
                               var type: String? = null,
                               var videostreams: List<PlayerVideoStream> = listOf())