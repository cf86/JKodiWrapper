package com.cf.jkodiwrapper.methods.audio.params.entity

import com.cf.jkodiwrapper.types.library.LibraryID

data class SongDetails(var songID: LibraryID,
                       var title: String? = null,
                       var artist: List<String>? = null,
                       var albumartist: List<String>? = null,
                       var genre: List<String>? = null,
                       var year: Int? = null,
                       var rating: Double? = null,
                       var album: String? = null,
                       var track: Int? = null,
                       var disc: Int? = null,
                       var duration: Int? = null,
                       var comment: String? = null,
                       var musicbrainztrackID: String? = null,
                       var musicbrainzartistID: String? = null,
                       var musicbrainzalbumID: String? = null,
                       var musicbrainzalbumartistID: String? = null,
                       var playcount: Int? = null,
                       var lastplayed: String? = null,
                       var userrating: Int? = null,
                       var votes: Int? = null)