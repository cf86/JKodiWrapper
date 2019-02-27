package com.cf.jkodiwrapper.methods.favourites.respond.entity

import com.cf.jkodiwrapper.types.favourite.FavouriteDetailsFavourite
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Favourites @JvmOverloads constructor(var favourites: List<FavouriteDetailsFavourite> = listOf(),
                                                var limits: ListLimitsReturned? = null)