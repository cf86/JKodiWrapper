package com.cf.jkodiwrapper.types.favourite

enum class FavouriteFieldsFavourite(var field: String) {

    WINDOW("window"),
    WINDOW_PARAMETER("windowparameter"),
    THUMBNAIL("thumbnail"),
    PATH("path");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<FavouriteFieldsFavourite> {
            return FavouriteFieldsFavourite.values().asList()
        }
    }
}