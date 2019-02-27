package com.cf.jkodiwrapper.methods.player.params.entity.speed

import com.cf.jkodiwrapper.types.player.PlayerSpeed

data class SpeedLevel(var speed: PlayerSpeed) : AbstractSpeed() {

    override fun getValue(): String {
        return speed.speed.toString()
    }
}