package com.cf.jkodiwrapper.methods.player.params.entity.stream

data class StreamIndexSelection(var index: Int) : AbstractStreamSelection() {

    override fun getValue(): String {
        return index.toString()
    }
}