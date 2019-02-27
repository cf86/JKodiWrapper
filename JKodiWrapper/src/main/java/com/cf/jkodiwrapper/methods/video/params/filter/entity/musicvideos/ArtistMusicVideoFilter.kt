package com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos

data class ArtistMusicVideoFilter(var artist: String) : AbstractMusicVideoFilter() {

    override fun toJSON(): String {
        return "\"artist\":\"$artist\""
    }
}