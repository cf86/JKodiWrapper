package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class GenreMusicVideoFilter(var genre: String) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"genre\":\"$genre\""
    }
}