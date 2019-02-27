package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class YearMusicVideoFilter(var year: Int) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"year\":$year"
    }
}