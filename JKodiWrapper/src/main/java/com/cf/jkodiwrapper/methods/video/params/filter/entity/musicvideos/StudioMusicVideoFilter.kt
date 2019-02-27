package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class StudioMusicVideoFilter(var studio: String) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"studio\":\"$studio\""
    }
}