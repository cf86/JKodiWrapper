package com.cf.jkodiwrapper.methods.audio.params.entity

import com.cf.jkodiwrapper.types.library.LibraryID

data class AlbumDetails(var albumID: LibraryID,
                        var title: String? = null,
                        var displayArtists: List<String>? = null,
                        var description: String? = null,
                        var genre: List<String>? = null,
                        var theme: List<String>? = null,
                        var mood: List<String>? = null,
                        var style: List<String>? = null,
                        var type: String? = null,
                        var albumLabel: String? = null,
                        var rating: Double? = null,
                        var year: Int? = null,
                        var userrating: Int? = null,
                        var votes: Int? = null)