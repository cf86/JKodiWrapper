package com.cf.jkodiwrapper.types.favourite

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FavouriteDetailsFavourite(var path: String? = null,
                                     var thumbnail: String? = null,
                                     var title: String? = null,
                                     var type: String? = null,
                                     var window: String? = null,
                                     var windowparameter: String? = null)