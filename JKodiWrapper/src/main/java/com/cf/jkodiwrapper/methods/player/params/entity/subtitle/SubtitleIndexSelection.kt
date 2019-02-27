package com.cf.jkodiwrapper.methods.player.params.entity.subtitle

data class SubtitleIndexSelection(var index: Int) : AbstractSubtitleSelection() {

    override fun getValue(): String {
        return index.toString()
    }
}