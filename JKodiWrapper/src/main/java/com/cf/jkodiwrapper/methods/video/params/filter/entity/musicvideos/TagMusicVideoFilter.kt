package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class TagMusicVideoFilter(var tag: String) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"tag\":\"$tag\""
    }
}