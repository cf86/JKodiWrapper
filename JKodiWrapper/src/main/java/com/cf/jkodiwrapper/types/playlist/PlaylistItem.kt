package com.cf.jkodiwrapper.types.playlist

import com.cf.jkodiwrapper.types.library.LibraryID
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistItem(var file: String? = null,
                        var directory: String? = null,
                        var movieid: LibraryID? = null,
                        var episodeid: LibraryID? = null,
                        var musicvideoid: LibraryID? = null,
                        var artistid: LibraryID? = null,
                        var albumid: LibraryID? = null,
                        var songid: LibraryID? = null,
                        var genreid: LibraryID? = null)