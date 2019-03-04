package com.cf.jkodiwrapper.types.player

enum class PlayerRepeat(var state: String) {

    OFF("off"),
    ONE("one"),
    ALL("all");

    override fun toString(): String {
        return state
    }

    companion object {
        @JvmStatic
        fun generatePlayerRepeat(state: String): PlayerRepeat? {
            return PlayerRepeat.values().find { it.state == state }
        }
    }
}