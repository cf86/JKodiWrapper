package com.cf.jkodiwrapper.methods.favourites.params

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.favourite.FavouriteType

data class AddFavParam(var title: String,
                       var type: FavouriteType,
                       var path: String?,
                       var window: String?,
                       var windowparameter: String?,
                       var thumbnail: String?) : KodiPostParam() {

    override fun toJSON(): String {
        var result = "\"title\":\"$title\",\"type\":\"$type\","
        if (path != null)
            result += "\"path\":\"$path\","
        if (window != null)
            result += "\"window\":\"$window\","
        if (windowparameter != null)
            result += "\"windowparameter\":\"$windowparameter\","
        if (thumbnail != null)
            result += "\"thumbnail\":\"$thumbnail\","

        return result.substring(0, result.lastIndex)
    }
}