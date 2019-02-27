package com.cf.jkodiwrapper.methods.favourites

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class FavouritesMethod(var methodName: String) : KodiMethod {

    ADD_FAVOURITE("Favourites.AddFavourite"),
    GET_FAVOURITES("Favourites.GetFavourites");

    override fun toString(): String {
        return methodName
    }
}