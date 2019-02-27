package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class ListFilterRule(var operator: ListFilterOperators,
                          var value: List<String> = listOf())