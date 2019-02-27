package com.cf.jkodiwrapper.types.player

data class PlayerPositionPercentage(var percent: Double) {

    override fun toString(): String {
        return percent.toString()
    }
}