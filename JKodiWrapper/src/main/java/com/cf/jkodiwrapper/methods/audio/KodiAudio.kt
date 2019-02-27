package com.cf.jkodiwrapper.methods.audio

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiBoolParam
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringArrParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.audio.params.entity.AlbumDetails
import com.cf.jkodiwrapper.methods.audio.params.entity.ArtistDetails
import com.cf.jkodiwrapper.methods.audio.params.entity.SongDetails
import com.cf.jkodiwrapper.methods.audio.params.entity.filter.album.AbstractAlbumFilter
import com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist.AbstractArtistFilter
import com.cf.jkodiwrapper.methods.audio.params.entity.filter.song.AbstractSongFilter
import com.cf.jkodiwrapper.methods.audio.params.post.*
import com.cf.jkodiwrapper.methods.audio.respond.*
import com.cf.jkodiwrapper.types.audio.*
import com.cf.jkodiwrapper.types.library.LibraryFieldsGenre
import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.list.ListAmount
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import org.slf4j.LoggerFactory

class KodiAudio @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Quit application
     *
     * @param id the ID which should be used for the call
     * @param showDialogs true if a dialog should be shown, else false
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun clean(id: KodiID, showDialogs: Boolean = false): KodiStringRespond {
        logger.info("Clean DB: Show Dialogs: $showDialogs. ID: $id")
        val post = KodiPostData(id, AudioMethod.CLEAN, KodiBoolParam("showdialogs", showDialogs))
        return makeCall(post)
    }

    /**
     * Retrieve all albums from specified artist (and role) or that has songs of the specified genre
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param filter the filter which should be applied. All filters can be found in AbstractAlbumFilter.get...() or for field filters use ListFilterAlbums.get...() to concatenate And, Or or FilterRules
     * @param limits limits the number of albums. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the albums
     * @param includeSingles include singles als Albums
     * @param allRoles Whether or not to include all roles when filtering by artist, rather than the default of excluding other contributions. When true it overrides any role filter value
     *
     * @return a list with all matching albums
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getAlbums(id: KodiID, properties: List<AudioFieldsAlbum> = listOf(), filter: AbstractAlbumFilter? = null, limits: ListLimits = ListLimits(), sort: ListSort = ListSort(), includeSingles: Boolean = false,
                  allRoles: Boolean = false): AlbumsRespond {
        logger.info("Get All Albums. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_ALBUMS, GetAlbumsParam(properties, filter, limits, sort, includeSingles, allRoles))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific album
     *
     * @param id the ID which should be used for the call
     * @param albumID the album ID of the album
     * @param properties the properties which should be requested. Use AudioFieldsAlbum.getAllFields() to get all fields.
     *
     * @return detailed information about the album
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getAlbumDetails(id: KodiID, albumID: LibraryID, properties: List<AudioFieldsAlbum> = listOf()): AlbumRespond {
        logger.info("Get All Album Details: $albumID. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_ALBUM_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("albumid", albumID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all artists. For backward compatibility by default this implicity does not include those that only contribute other roles, however absolutely all artists can be returned using allroles=true
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsArtist.getAllFields() to get all fields.
     * @param filter the filter which should be applied. All filters can be found in AbstractArtistFilter.get...() or for field filters use ListFilterArtists.get...() to concatenate And, Or or FilterRules
     * @param limits limits the number of albums. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the artists
     * @param albumArtistsOnly Whether or not to only include album artists rather than the artists of only individual songs as well. If the parameter is not passed or is passed as null the GUI setting will be used
     * @param allRoles Whether or not to include all roles when filtering by artist, rather than the default of excluding other contributions. When true it overrides any role filter value
     *
     * @return a list with all artists which match the filter
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getArtists(id: KodiID, properties: List<AudioFieldsArtist> = listOf(), filter: AbstractArtistFilter? = null, limits: ListLimits = ListLimits(), sort: ListSort = ListSort(), albumArtistsOnly: Boolean = false,
                   allRoles: Boolean = false): ArtistsRespond {
        logger.info("Get All Artists. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_ARTISTS, GetArtistsParam(properties, filter, limits, sort, albumArtistsOnly, allRoles))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific artist
     *
     * @param id the ID which should be used for the call
     * @param artistID the ID of the artist to get details for
     * @param properties the properties which should be requested. Use AudioFieldsArtist.getAllFields() to get all fields.
     *
     * @return detailed information about the artist
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getArtistDetails(id: KodiID, artistID: LibraryID, properties: List<AudioFieldsArtist> = listOf()): ArtistRespond {
        logger.info("Get Artist: $artistID. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_ARTIST_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("artistid", artistID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all genres
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use LibraryFieldsGenre.getAllFields() to get all fields.
     * @param limits limits the number of albums. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the albums
     *
     * @return list with all used genres in the archive
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getGenres(id: KodiID, properties: List<LibraryFieldsGenre> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): GenresRespond {
        logger.info("Get Genres. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_GENRES, GetGenresParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieves the values of the music library properties
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use LibraryFieldsGenre.getAllFields() to get all fields.
     *
     * @return the requested propery
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getProperties(id: KodiID, properties: List<AudioPropertyName> = listOf()): PropertiesRespond {
        logger.info("Get Properties. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_PROPERTIES, KodiStringArrParam("properties", properties.map { it.field }))
        return makeCall(post)
    }

    /**
     * Retrieve recently added albums
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsAlbum.getAllFields() to get all fields.
     * @param limits limits the number of albums. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the albums
     *
     * @return list with the recently added albums
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyAddedAlbums(id: KodiID, properties: List<AudioFieldsAlbum> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): AlbumsRespond {
        logger.info("Get recently added Albums. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_RECENTLY_ADDED_ALBUMS, GetRecAddedAlbumsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve recently added songs
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsSong.getAllFields() to get all fields.
     * @param limits limits the number of songs. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the songs
     * @param albumLimit The amount of recently added albums from which to return the songs
     *
     * @return list with the recently added songs
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyAddedSongs(id: KodiID, properties: List<AudioFieldsSong> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort(), albumLimit: ListAmount = ListAmount()): SongsRespond {
        logger.info("Get recently added Songs. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_RECENTLY_ADDED_SONGS, GetRecAddedSongsParam(properties, limits, sort, albumLimit))
        return makeCall(post)
    }

    /**
     * Retrieve recently played albums
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsAlbum.getAllFields() to get all fields.
     * @param limits limits the number of albums. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the albums
     *
     * @return list with the recently played albums
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyPlayedAlbums(id: KodiID, properties: List<AudioFieldsAlbum> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): AlbumsRespond {
        logger.info("Get recently played Albums. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_RECENTLY_PLAYED_ALBUMS, GetRecPlayedAlbumsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve recently played songs
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsSong.getAllFields() to get all fields.
     * @param limits limits the number of songs. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the songs
     *
     * @return list with the recently played songs
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyPlayedSongs(id: KodiID, properties: List<AudioFieldsSong> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): SongsRespond {
        logger.info("Get recently added Songs. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_RECENTLY_ADDED_SONGS, GetRecPlayedSongsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all contributor roles
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsRole.getAllFields() to get all fields.
     * @param limits limits the number of roles. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the roles
     *
     * @return list with the roles
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRoles(id: KodiID, properties: List<AudioFieldsRole> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): RolesRespond {
        logger.info("Get Roles. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_ROLES, GetRolesParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific song
     *
     * @param id the ID which should be used for the call
     * @param songID the song library ID
     * @param properties the properties which should be requested. Use AudioFieldsSong.getAllFields() to get all fields.
     *
     * @return song details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getSongDetails(id: KodiID, songID: LibraryID, properties: List<AudioFieldsSong> = listOf()): SongDetailsRespond {
        logger.info("Get song details for $songID. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_SONG_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("songid", songID.id), KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all songs from specified album, artist or genre
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested. Use AudioFieldsSong.getAllFields() to get all fields.
     * @param filter the filter which should be applied. All filters can be found in AbstractSongFilter.get...() or for field filters use ListFilterSongs.get...() to concatenate And, Or or FilterRules
     * @param limits limits the number of roles. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the songs
     * @param includeSingles true to include singles, else false
     * @param allRoles Whether or not to include all roles when filtering by artist, rather than default of excluding other contributors. When true it overrides any role filter value
     *
     * @return list with the songs
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getSongs(id: KodiID, properties: List<AudioFieldsSong> = listOf(), filter: AbstractSongFilter? = null, limits: ListLimits = ListLimits(), sort: ListSort = ListSort(),
                 includeSingles: Boolean = true, allRoles: Boolean = false): SongsRespond {
        logger.info("Get Songs. ID: $id")
        val post = KodiPostData(id, AudioMethod.GET_SONGS, GetSongsParam(properties, filter, limits, sort, includeSingles, allRoles))
        return makeCall(post)
    }

    /**
     * Retrieve all songs from specified album, artist or genre
     *
     * @param id the ID which should be used for the call
     * @param directory the directory as absolute path which should be scanned. For Windows use C:\\\\path\\\\to\\\\dir. In Unix /path/to/dir
     * @param showDialogs true if a dialog in kodi should be shown, else false
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun scan(id: KodiID, directory: KodiPath, showDialogs: Boolean = true): KodiStringRespond {
        logger.info("Scan library: $directory. ID: $id")
        val post = KodiPostData(id, AudioMethod.SCAN, KodiPropertiesParam(listOf(KodiStringProperty("directory", directory.path),
                KodiBoolProperty("showdialogs", showDialogs))))
        return makeCall(post)
    }

    /**
     * Retrieve all songs from specified album, artist or genre
     *
     * @param id the ID which should be used for the call
     * @param details the details to set for the specific album. if a value is null, it will not be overwritten
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setAlbumDetails(id: KodiID, details: AlbumDetails): KodiStringRespond {
        logger.info("Set Album Details: $details. ID: $id")
        val post = KodiPostData(id, AudioMethod.SET_ALBUM_DETAILS, SetAlbumDetailsParam(details))
        return makeCall(post)
    }

    /**
     * Retrieve all songs from specified album, artist or genre
     *
     * @param id the ID which should be used for the call
     * @param details the details to set for the specific artist. if a value is null, it will not be overwritten
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setArtistDetails(id: KodiID, details: ArtistDetails): KodiStringRespond {
        logger.info("Set Album Details: $details. ID: $id")
        val post = KodiPostData(id, AudioMethod.SET_ARTIST_DETAILS, SetArtistDetailsParam(details))
        return makeCall(post)
    }

    /**
     * Retrieve all songs from specified album, artist or genre
     *
     * @param id the ID which should be used for the call
     * @param details the details to set for the specific song. if a value is null, it will not be overwritten
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setSongDetails(id: KodiID, details: SongDetails): KodiStringRespond {
        logger.info("Set Album Details: $details. ID: $id")
        val post = KodiPostData(id, AudioMethod.SET_SONG_DETAILS, SetSongDetailsParam(details))
        return makeCall(post)
    }
}