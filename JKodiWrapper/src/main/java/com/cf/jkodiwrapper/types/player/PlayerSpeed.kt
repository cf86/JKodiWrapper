package com.cf.jkodiwrapper.types.player

data class PlayerSpeed(var speed: Int) {

    companion object {
        val SPEED_MINUS_32 = PlayerSpeed(-32)
        val SPEED_MINUS_16 = PlayerSpeed(-16)
        val SPEED_MINUS_8 = PlayerSpeed(-8)
        val SPEED_MINUS_4 = PlayerSpeed(-4)
        val SPEED_MINUS_2 = PlayerSpeed(-2)
        val SPEED_MINUS_1 = PlayerSpeed(-1)
        val SPEED_0 = PlayerSpeed(0)
        val SPEED_1 = PlayerSpeed(1)
        val SPEED_2 = PlayerSpeed(2)
        val SPEED_4 = PlayerSpeed(4)
        val SPEED_8 = PlayerSpeed(8)
        val SPEED_16 = PlayerSpeed(16)
        val SPEED_32 = PlayerSpeed(32)
    }

    override fun toString(): String {
        return speed.toString()
    }
}