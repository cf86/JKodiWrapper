package com.cf.jkodiwrapper.methods.audio.params.entity

import com.cf.jkodiwrapper.types.library.LibraryID

data class ArtistDetails(var artistID: LibraryID,
                         var artist: String? = null,
                         var instrument: List<String>? = null,
                         var style: List<String>? = null,
                         var mood: List<String>? = null,
                         var born: String? = null,
                         var formed: String? = null,
                         var description: String? = null,
                         var genre: List<String>? = null,
                         var died: String? = null,
                         var disbanded: String? = null,
                         var yearsactive: List<String>? = null)