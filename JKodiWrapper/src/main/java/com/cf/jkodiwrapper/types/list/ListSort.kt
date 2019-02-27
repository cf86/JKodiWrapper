package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.types.list.entity.Order
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListSort(var ignorearticle: Boolean = false,
                    var method: String = "none",
                    var order: Order = Order.ASCENDING)