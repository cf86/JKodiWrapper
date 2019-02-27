package com.cf.jkodiwrapper.types.system

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SystemPropertyValue(var canhibernate: Boolean = false,
                               var canreboot: Boolean = false,
                               var canshutdown: Boolean = false,
                               var cansuspend: Boolean = false)