package com.cf.jkodiwrapper.types.video

import com.cf.jkodiwrapper.types.video.entity.Audio
import com.cf.jkodiwrapper.types.video.entity.Subtitle
import com.cf.jkodiwrapper.types.video.entity.Video
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VideoStreams(var audio: List<Audio> = listOf(),
                        var subtitle: List<Subtitle> = listOf(),
                        var video: List<Video> = listOf())