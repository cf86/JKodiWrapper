package com.cf.jkodiwrapper.methods.application.params.entity.volume

data class LevelVolume(var volume: Int) : AbstractVolume() {

    override fun isValid(): Boolean {
        return volume in 1..100
    }

    override fun getValue(): String {
        return volume.toString()
    }
}