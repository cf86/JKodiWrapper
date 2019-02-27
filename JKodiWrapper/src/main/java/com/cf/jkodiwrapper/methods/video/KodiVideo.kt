package com.cf.jkodiwrapper.methods.video

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.video.params.filter.entity.episode.AbstractEpisodesFilter
import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter
import com.cf.jkodiwrapper.methods.video.params.filter.entity.musicvideos.AbstractMusicVideoFilter
import com.cf.jkodiwrapper.methods.video.params.filter.entity.tvshows.AbstractTvShowFilter
import com.cf.jkodiwrapper.methods.video.params.filter.post.*
import com.cf.jkodiwrapper.methods.video.respond.*
import com.cf.jkodiwrapper.types.library.LibraryFieldsGenre
import com.cf.jkodiwrapper.types.library.LibraryFieldsTag
import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.video.*
import org.slf4j.LoggerFactory

class KodiVideo @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Scans the video sources for new library items
     *
     * @param id the ID which should be used for the call
     * @param directory the directory as absolute path which should be scanned. For Windows use C:\\\\path\\\\to\\\\dir. In Unix /path/to/dir
     * @param showDialogs true if a dialog in kodi should be shown, else false
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun scan(id: KodiID, directory: KodiPath, showDialogs: Boolean = true): KodiStringRespond {
        logger.info("Scan Video Directory: $directory ID: $id")
        val post = KodiPostData(id, VideoMethod.SCAN, KodiPropertiesParam(listOf(KodiStringProperty("directory", directory.path),
                KodiBoolProperty("showdialogs", showDialogs))))
        return makeCall(post)
    }

    /**
     * Cleans the video library from non-existent items
     *
     * @param id the ID which should be used for the call
     * @param showDialogs true if a dialog in kodi should be shown, else false
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun clean(id: KodiID, showDialogs: Boolean = true): KodiStringRespond {
        logger.info("Clean video library. ID: $id")
        val post = KodiPostData(id, VideoMethod.CLEAN, KodiPropertiesParam(listOf(KodiBoolProperty("showdialogs", showDialogs))))
        return makeCall(post)
    }

    /**
     * Retrieve all movies
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of movies. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the movies
     * @param filter the filter which should be applied. All filters can be found in AbstractMovieFilter.get...() or for field filters use ListFilterMovies.get...() to concatenate And, Or or FilterRules
     *
     * @return a list with all matching movies
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMovies(id: KodiID, properties: List<VideoFieldsMovie> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort(),
                  filter: AbstractMovieFilter? = null): MoviesRespond {
        logger.info("Get All Movies. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MOVIES, GetMoviesParam(properties, limits, sort, filter))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific movie
     *
     * @param id the ID which should be used for the call
     * @param movieID the movieID to get
     * @param properties the properties which should be requested
     *
     * @return the movie details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMovieDetails(id: KodiID, movieID: LibraryID, properties: List<VideoFieldsMovie> = listOf()): MovieDetailsRespond {
        logger.info("Get Movie Details for $movieID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MOVIE_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("movieid", movieID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all tv show episodes
     *
     * @param id the ID which should be used for the call
     * @param tvShowID the TV show ID the episodes should belong to
     * @param season the season
     * @param properties the properties which should be requested
     * @param limits limits the number of episodes. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the episodes
     * @param filter the filter which should be applied. All filters can be found in AbstractEpisodesFilter.get...() or for field filters use ListFilterEpisodes.get...() to concatenate And, Or or FilterRules
     *
     * @return a list with all matching episodes
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getEpisodes(id: KodiID, tvShowID: LibraryID, season: Int, properties: List<VideoFieldsEpisode> = listOf(),
                    limits: ListLimits = ListLimits(), sort: ListSort = ListSort(), filter: AbstractEpisodesFilter? = null): EpisodesRespond {
        logger.info("Get All Episodes for $tvShowID and season: $season. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_EPISODES, GetEpisodesParam(tvShowID, season, properties, limits, sort, filter))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific tv show episode
     *
     * @param id the ID which should be used for the call
     * @param episodeID the episode ID to get
     * @param properties the properties which should be requested
     *
     * @return the episode details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getEpisodeDetails(id: KodiID, episodeID: LibraryID, properties: List<VideoFieldsEpisode> = listOf()): EpisodeDetailsRespond {
        logger.info("Get Episode Details for $episodeID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_EPISODE_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("episodeid", episodeID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all genres
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of genres. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the genres
     *
     * @return the genres
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getGenres(id: KodiID, properties: List<LibraryFieldsGenre>, limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): GenresRespond {
        logger.info("Get Genres. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_GENRES, GetGenresParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all genres
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of genres. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the genres
     *
     * @return the genres
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getInProgressTvShows(id: KodiID, properties: List<VideoFieldsTVShow>, limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): InProgressTvShowsRespond {
        logger.info("Get Genres. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_IN_PROGRESS_TV_SHOWS, GetInProgressTVShowsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all movie sets
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of movie sets. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the movie sets
     *
     * @return the movie sets
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMovieSets(id: KodiID, properties: List<VideoFieldsMovieSet>, limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): MovieSetsRespond {
        logger.info("Get Movie Sets. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MOVIE_SETS, GetMovieSetsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific movie set
     *
     * @param id the ID which should be used for the call
     * @param setID the movie set ID to get details for
     * @param properties the properties which should be requested
     *
     * @return the movie set details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMovieSetDetails(id: KodiID, setID: LibraryID, properties: List<VideoFieldsMovieSet> = listOf()): MovieSetDetailsRespond {
        logger.info("Get Movie Set Details for $setID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MOVIE_SET_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("setid", setID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all music videos
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of music videos. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the music videos
     * @param filter the filter which should be applied. All filters can be found in AbstractMusicVideoFilter.get...() or for field filters use ListFilterMusicVideos.get...() to concatenate And, Or or FilterRules
     *
     * @return the music videos
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMusicVideos(id: KodiID, properties: List<VideoFieldsMovieSet> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort(),
                       filter: AbstractMusicVideoFilter? = null): MusicVideosRespond {
        logger.info("Get Music Videos. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MUSIC_VIDEOS, GetMusicVideosParam(properties, limits, sort, filter))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific music video
     *
     * @param id the ID which should be used for the call
     * @param musicVideoID the music video id to get details for
     * @param properties the properties which should be requested
     *
     * @return the music video details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getMusicVideoDetails(id: KodiID, musicVideoID: LibraryID, properties: List<VideoFieldsMovieSet> = listOf()): MusicVideoDetailsRespond {
        logger.info("Get Music Video Details for $musicVideoID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_MUSIC_VIDEO_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("musicvideoid", musicVideoID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all recently added tv episodes
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of episodes. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the episodes
     *
     * @return a list with all recently added episodes
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyAddedEpisodes(id: KodiID, properties: List<VideoFieldsEpisode> = listOf(),
                                 limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): EpisodesRespond {
        logger.info("Get All recently Episodes. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_RECENTLY_ADDED_EPISODES, GetRecentlyAddedEpisodesParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all recently added movies
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of movies. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the movies
     *
     * @return a list with all recently added movies
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyAddedMovies(id: KodiID, properties: List<VideoFieldsMovie> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): MoviesRespond {
        logger.info("Get all recently added Movies. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_RECENTLY_ADDED_MOVIES, GetRecentlyAddedMoviesParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all recently added music videos
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of music videos. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the music videos
     *
     * @return a list with all recently added music videos
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getRecentlyAddedMusicVideos(id: KodiID, properties: List<VideoFieldsMovieSet> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): MusicVideosRespond {
        logger.info("Get all recently added Music Videos. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_RECENTLY_ADDED_MUSIC_VIDEOS, GetRecentlyAddedMusicVideosParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all tv seasons
     *
     * @param id the ID which should be used for the call
     * @param tvShowID the tv show id for which the seasons should be gotten
     * @param properties the properties which should be requested
     * @param limits limits the number of seasons. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the seasons
     *
     * @return a list with all seasons of the specified tv show
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getSeasons(id: KodiID, tvShowID: LibraryID, properties: List<VideoFieldsSeason> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): SeasonsRespond {
        logger.info("Get all Seasons for show $tvShowID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_SEASONS, GetSeasonsParam(tvShowID, properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve details about a specific tv show season
     *
     * @param id the ID which should be used for the call
     * @param seasonID the season id to get details for
     * @param properties the properties which should be requested
     *
     * @return the season details
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getSeasonDetails(id: KodiID, seasonID: LibraryID, properties: List<VideoFieldsSeason> = listOf()): SeasonDetailsRespond {
        logger.info("Get Season Details for $seasonID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_SEASON_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("seasonid", seasonID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieve all genres
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of genres. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the genres
     *
     * @return the genres
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getTags(id: KodiID, properties: List<LibraryFieldsTag>, limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): TagsRespond {
        logger.info("Get Genres. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_TAGS, GetTagsParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all tv shows
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be requested
     * @param limits limits the number of tv shows. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the tv shows
     * @param filter the filter which should be applied. All filters can be found in AbstractTvShowFilter.get...() or for field filters use ListFilterTvShows.get...() to concatenate And, Or or FilterRules
     *
     * @return a list of matching tv shows
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getTVShows(id: KodiID, properties: List<VideoFieldsTVShow>, limits: ListLimits = ListLimits(), sort: ListSort = ListSort(),
                   filter: AbstractTvShowFilter? = null): TvShowsRespond {
        logger.info("Get all TV Shows. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_TV_SHOWS, GetTvShowsParam(properties, limits, sort, filter))
        return makeCall(post)
    }

    /**
     * Retrieve all tv shows
     *
     * @param id the ID which should be used for the call
     * @param tvShowID the tv show ID to get the details for
     * @param properties the properties which should be requested
     *
     * @return a list of matching tv shows
     */
    @Throws(KodiException::class)
    fun getTVShowDetails(id: KodiID, tvShowID: LibraryID, properties: List<VideoFieldsTVShow>): TvShowDetailsRespond {
        logger.info("Get TV Show details for $tvShowID. ID: $id")
        val post = KodiPostData(id, VideoMethod.GET_TV_SHOW_DETAILS, KodiPropertiesParam(listOf(KodiIntProperty("tvshowid", tvShowID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Refresh the given episode in the library
     *
     * @param id the ID which should be used for the call
     * @param episodeID the episode ID to refresh
     * @param ignoreInfo Whether or not to ignore a local NFO if present.
     * @param title Title to use for searching (instead of determining it from the item's filename/path).
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun refreshEpisode(id: KodiID, episodeID: LibraryID, ignoreInfo: Boolean = false, title: String? = null): KodiStringRespond {
        logger.info("Refresh Episode $episodeID. ID: $id")
        val params = mutableListOf(KodiIntProperty("episodeid", episodeID.id), KodiBoolProperty("ignorenfo", ignoreInfo))
        if (title != null)
            params.add(KodiStringProperty("title", title))
        val post = KodiPostData(id, VideoMethod.REFRESH_EPISODE, KodiPropertiesParam(params))
        return makeCall(post)
    }

    /**
     * Refresh the given movie in the library
     *
     * @param id the ID which should be used for the call
     * @param movieID the movie ID to refresh
     * @param ignoreInfo Whether or not to ignore a local NFO if present.
     * @param title Title to use for searching (instead of determining it from the item's filename/path).
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun refreshMovie(id: KodiID, movieID: LibraryID, ignoreInfo: Boolean = false, title: String? = null): KodiStringRespond {
        logger.info("Refresh Movie $movieID. ID: $id")
        val params = mutableListOf(KodiIntProperty("movieid", movieID.id), KodiBoolProperty("ignorenfo", ignoreInfo))
        if (title != null)
            params.add(KodiStringProperty("title", title))
        val post = KodiPostData(id, VideoMethod.REFRESH_MOVIE, KodiPropertiesParam(params))
        return makeCall(post)
    }

    /**
     * Refresh the given music video in the library
     *
     * @param id the ID which should be used for the call
     * @param musicVideoID the music video ID to refresh
     * @param ignoreInfo Whether or not to ignore a local NFO if present.
     * @param title Title to use for searching (instead of determining it from the item's filename/path).
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun refreshMusicVideo(id: KodiID, musicVideoID: LibraryID, ignoreInfo: Boolean = false, title: String? = null): KodiStringRespond {
        logger.info("Refresh Music Video $musicVideoID. ID: $id")
        val params = mutableListOf(KodiIntProperty("musicvideoid", musicVideoID.id), KodiBoolProperty("ignorenfo", ignoreInfo))
        if (title != null)
            params.add(KodiStringProperty("title", title))
        val post = KodiPostData(id, VideoMethod.REFRESH_MUSIC_VIDEO, KodiPropertiesParam(params))
        return makeCall(post)
    }

    /**
     * Refresh the given TV Show in the library
     *
     * @param id the ID which should be used for the call
     * @param tvShowID the TV show ID to refresh
     * @param ignoreInfo Whether or not to ignore a local NFO if present.
     * @param refreshEpisodes Whether or not to refresh all episodes belonging to the TV show.
     * @param title Title to use for searching (instead of determining it from the item's filename/path).
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun refreshTvShow(id: KodiID, tvShowID: LibraryID, ignoreInfo: Boolean = false, refreshEpisodes: Boolean = false, title: String? = null): KodiStringRespond {
        logger.info("Refresh TV Show $tvShowID. ID: $id")
        val params = mutableListOf(KodiIntProperty("tvshowid", tvShowID.id), KodiBoolProperty("ignorenfo", ignoreInfo), KodiBoolProperty("refreshepisodes", refreshEpisodes))
        if (title != null)
            params.add(KodiStringProperty("title", title))
        val post = KodiPostData(id, VideoMethod.REFRESH_TV_SHOW, KodiPropertiesParam(params))
        return makeCall(post)
    }

    /**
     * Removes the given episode from the library
     *
     * @param id the ID which should be used for the call
     * @param episodeID the episode ID which should be removed
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun removeEpisode(id: KodiID, episodeID: LibraryID): KodiStringRespond {
        logger.info("Remove Episode $episodeID. ID: $id")
        val post = KodiPostData(id, VideoMethod.REMOVE_EPISODE, KodiPropertiesParam(listOf(KodiIntProperty("episodeid", episodeID.id))))
        return makeCall(post)
    }

    /**
     * Removes the given movie from the library
     *
     * @param id the ID which should be used for the call
     * @param movieID the movie ID which should be removed
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun removeMovie(id: KodiID, movieID: LibraryID): KodiStringRespond {
        logger.info("Remove Movie $movieID. ID: $id")
        val post = KodiPostData(id, VideoMethod.REMOVE_MOVIE, KodiPropertiesParam(listOf(KodiIntProperty("movieid", movieID.id))))
        return makeCall(post)
    }

    /**
     * Removes the given music video from the library
     *
     * @param id the ID which should be used for the call
     * @param musicVideoID the music video ID which should be removed
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun removeMusicVideo(id: KodiID, musicVideoID: LibraryID): KodiStringRespond {
        logger.info("Remove Music Video ID $musicVideoID. ID: $id")
        val post = KodiPostData(id, VideoMethod.REMOVE_MUSIC_VIDEO, KodiPropertiesParam(listOf(KodiIntProperty("musicvideoid", musicVideoID.id))))
        return makeCall(post)
    }

    /**
     * Removes the given tv show from the library
     *
     * @param id the ID which should be used for the call
     * @param tvShowID the TV Show ID which should be removed
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun removeTVShow(id: KodiID, tvShowID: LibraryID): KodiStringRespond {
        logger.info("Remove TV Show. ID $tvShowID. ID: $id")
        val post = KodiPostData(id, VideoMethod.REMOVE_TV_SHOW, KodiPropertiesParam(listOf(KodiIntProperty("tvshowid", tvShowID.id))))
        return makeCall(post)
    }
}


fun main(args: Array<String>) {
    val k = KodiVideo("127.0.0.1", 8080)
    val r = k.refreshMovie(KodiID(1), LibraryID(1))
    println(r)
}