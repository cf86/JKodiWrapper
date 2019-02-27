package com.cf.jkodiwrapper.methods.player.params.entity.zoom

import com.cf.jkodiwrapper.types.player.PlayerZoomOperation

data class ZoomOperation(var inOut: PlayerZoomOperation) : AbstractZoom() {

    override fun getValue(): String {
        return inOut.op
    }

    override fun isValid(): Boolean {
        return true
    }
}