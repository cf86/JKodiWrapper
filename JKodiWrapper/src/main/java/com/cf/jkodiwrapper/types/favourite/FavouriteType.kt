package com.cf.jkodiwrapper.types.favourite

enum class FavouriteType(var type: String) {

    MEDIA("media"),
    WINDOW("window"),
    SCRIPT("script"),
    UNKNOWN("unknown");

    override fun toString(): String {
        return type
    }
}