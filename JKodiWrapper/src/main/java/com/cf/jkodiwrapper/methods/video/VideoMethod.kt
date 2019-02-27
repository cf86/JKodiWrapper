package com.cf.jkodiwrapper.methods.video

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class VideoMethod(var methodName: String) : KodiMethod {

    CLEAN("VideoLibrary.Clean"),
    GET_EPISODE_DETAILS("VideoLibrary.GetEpisodeDetails"),
    GET_EPISODES(" VideoLibrary.GetEpisodes"),
    GET_GENRES("VideoLibrary.getGenres"),
    GET_IN_PROGRESS_TV_SHOWS("VideoLibrary.GetInProgressTVShows"),
    GET_MOVIE_DETAILS("VideoLibrary.GetMovieDetails"),
    GET_MOVIES("VideoLibrary.GetMovies"),
    GET_MOVIE_SET_DETAILS("VideoLibrary.GetMovieSetDetails"),
    GET_MOVIE_SETS("VideoLibrary.GetMovieSets"),
    GET_MUSIC_VIDEO_DETAILS("VideoLibrary.GetMusicVideoDetails"),
    GET_MUSIC_VIDEOS("VideoLibrary.GetMusicVideos"),
    GET_RECENTLY_ADDED_EPISODES("VideoLibrary.GetRecentlyAddedEpisodes"),
    GET_RECENTLY_ADDED_MOVIES("VideoLibrary.GetRecentlyAddedMovies"),
    GET_RECENTLY_ADDED_MUSIC_VIDEOS("VideoLibrary.GetRecentlyAddedMusicVideos"),
    GET_SEASON_DETAILS("VideoLibrary.GetSeasonDetails"),
    GET_SEASONS("VideoLibrary.GetSeasons"),
    GET_TAGS("VideoLibrary.GetTags"),
    GET_TV_SHOW_DETAILS("VideoLibrary.GetTVShowDetails"),
    GET_TV_SHOWS("VideoLibrary.GetTVShows"),
    REFRESH_EPISODE("VideoLibrary.RefreshEpisode"),
    REFRESH_MOVIE("VideoLibrary.RefreshMovie"),
    REFRESH_MUSIC_VIDEO("VideoLibrary.RefreshMusicVideo"),
    REFRESH_TV_SHOW("VideoLibrary.RefreshTVShow"),
    REMOVE_EPISODE("VideoLibrary.RemoveEpisode"),
    REMOVE_MOVIE("VideoLibrary.RemoveMovie"),
    REMOVE_MUSIC_VIDEO("VideoLibrary.RemoveMusicVideo"),
    REMOVE_TV_SHOW(" VideoLibrary.RemoveTVShow"),
    SCAN("VideoLibrary.Scan"),
    SET_EPISODE_DETAILS("VideoLibrary.SetEpisodeDetails"),
    SET_MOVIE_DETAILS("VideoLibrary.SetMovieDetails"),
    SET_MOVIE_SET_DETAILS("VideoLibrary.SetMovieSetDetails"),
    SET_MUSIC_VIDEO_DETAILS("VideoLibrary.SetMusicVideoDetails"),
    SET_SEASON_DETAILS("VideoLibrary.SetSeasonDetails"),
    SET_TV_SHOW_DETAILS("VideoLibrary.SetTVShowDetails");

    override fun toString(): String {
        return methodName
    }
}