package com.cf.jkodiwrapper.methods.player.params.entity.zoom

import com.cf.jkodiwrapper.types.player.PlayerZoomOperation

abstract class AbstractZoom {

    abstract fun getValue(): String

    abstract fun isValid(): Boolean

    companion object {
        @JvmStatic
        fun getZoomLevel(level: Int) = ZoomLevel(level)

        @JvmStatic
        fun getZoomOperation(inOut: PlayerZoomOperation) = ZoomOperation(inOut)
    }
}