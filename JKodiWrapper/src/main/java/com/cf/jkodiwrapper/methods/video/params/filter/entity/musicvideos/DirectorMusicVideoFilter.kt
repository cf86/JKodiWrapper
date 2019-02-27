package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class DirectorMusicVideoFilter(var director: String) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"director\":\"$director\""
    }
}