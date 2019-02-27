package com.cf.jkodiwrapper.methods.audio.respond.entity

import com.cf.jkodiwrapper.types.library.LibraryDetailsGenre
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Genres @JvmOverloads constructor(var genres: List<LibraryDetailsGenre> = listOf(),
                                            var limits: ListLimitsReturned? = null)