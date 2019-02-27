package com.cf.jkodiwrapper.methods.player.params.entity.zoom

data class ZoomLevel(var level: Int) : AbstractZoom() {

    override fun getValue(): String {
        return level.toString()
    }

    override fun isValid(): Boolean {
        return level in 1..10
    }
}