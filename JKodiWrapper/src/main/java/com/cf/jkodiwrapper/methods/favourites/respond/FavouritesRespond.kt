package com.cf.jkodiwrapper.methods.favourites.respond

import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.methods.favourites.respond.entity.Favourites
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FavouritesRespond(var result: Favourites? = null) : KodiRespond() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? FavouritesRespond ?: return false
        return result == obj.result && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + result.hashCode()
        return result
    }
}