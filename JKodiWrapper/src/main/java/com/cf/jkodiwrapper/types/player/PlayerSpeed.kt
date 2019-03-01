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

        @JvmStatic
        fun generateSpeed(speed: Int): PlayerSpeed {
            when (speed) {
                -32 -> return SPEED_MINUS_32
                -16 -> return SPEED_MINUS_16
                -8 -> return SPEED_MINUS_8
                -4 -> return SPEED_MINUS_4
                -2 -> return SPEED_MINUS_2
                -1 -> return SPEED_MINUS_1
                0 -> return SPEED_0
                1 -> return SPEED_1
                2 -> return SPEED_2
                4 -> return SPEED_4
                8 -> return SPEED_8
                16 -> return SPEED_16
                32 -> return SPEED_32
                else -> return SPEED_0
            }
        }
    }

    override fun toString(): String {
        return speed.toString()
    }
}