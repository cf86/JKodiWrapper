package com.cf.jkodiwrapper.methods.player

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiEmptyParam
import com.cf.jkodiwrapper.general.request.param.post.KodiIntParam
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.player.params.entity.goto.AbstractGoTo
import com.cf.jkodiwrapper.methods.player.params.entity.goto.NxtPrevGoTo
import com.cf.jkodiwrapper.methods.player.params.entity.goto.PlaylistPosGoTo
import com.cf.jkodiwrapper.methods.player.params.entity.playermedia.AbstractPlayerMedia
import com.cf.jkodiwrapper.methods.player.params.entity.playermedia.PlaylistItemMedia
import com.cf.jkodiwrapper.methods.player.params.entity.seekto.AbstractSeekTo
import com.cf.jkodiwrapper.methods.player.params.entity.seekto.SeekBackForward
import com.cf.jkodiwrapper.methods.player.params.entity.seekto.SeekToPercentage
import com.cf.jkodiwrapper.methods.player.params.entity.seekto.SeekToTime
import com.cf.jkodiwrapper.methods.player.params.entity.speed.AbstractSpeed
import com.cf.jkodiwrapper.methods.player.params.entity.speed.IncrDecrSpeed
import com.cf.jkodiwrapper.methods.player.params.entity.speed.SpeedLevel
import com.cf.jkodiwrapper.methods.player.params.entity.stream.AbstractStreamSelection
import com.cf.jkodiwrapper.methods.player.params.entity.stream.PrevNextStreamSelection
import com.cf.jkodiwrapper.methods.player.params.entity.stream.StreamIndexSelection
import com.cf.jkodiwrapper.methods.player.params.entity.subtitle.AbstractSubtitleSelection
import com.cf.jkodiwrapper.methods.player.params.entity.subtitle.SubtitleIndexSelection
import com.cf.jkodiwrapper.methods.player.params.entity.subtitle.SubtitleOperationSelection
import com.cf.jkodiwrapper.methods.player.params.entity.zoom.AbstractZoom
import com.cf.jkodiwrapper.methods.player.params.entity.zoom.ZoomLevel
import com.cf.jkodiwrapper.methods.player.params.entity.zoom.ZoomOperation
import com.cf.jkodiwrapper.methods.player.params.post.OpenPlayerParam
import com.cf.jkodiwrapper.methods.player.params.post.SeekToParam
import com.cf.jkodiwrapper.methods.player.respond.*
import com.cf.jkodiwrapper.types.global.*
import com.cf.jkodiwrapper.types.list.ListFieldsAll
import com.cf.jkodiwrapper.types.player.*
import com.cf.jkodiwrapper.types.playlist.PlaylistItem
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition
import com.cf.jkodiwrapper.types.subtitle.SubtitleOperation
import org.slf4j.LoggerFactory

class KodiPlayer @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Start playback of either the playlist with the given ID, a slideshow with the pictures from the given directory or a single file or an item from the database.
     *
     * @param id the ID which should be used for the call
     * @param file an absolute path to a file e.g. C:\\\\path\\\\to\\\\file or /path/to/file, or an upnp file url
     * @param options additional options as a Map which should be added to the call
     *
     * @return OK if everything fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun openPlayer(id: KodiID, file: KodiPath, options: Map<String, String>? = null): KodiStringRespond {
        return openPlayer(id, PlaylistItemMedia(PlaylistItem(file.path)), options)
    }

    /**
     * Start playback of either the playlist with the given ID, a slideshow with the pictures from the given directory or a single file or an item from the database.
     *
     * @param id the ID which should be used for the call
     * @param media the media which should be played. Can be a file from DB, upnp, playlist item, recording, tv, or partymode. Use AbstractPlayerMedia.get...() to get a specific instance
     * @param options additional options as a Map which should be added to the call
     *
     * @return OK if everything fine, else error
     */
    @Throws(KodiException::class)
    private fun openPlayer(id: KodiID, media: AbstractPlayerMedia, options: Map<String, String>? = null): KodiStringRespond {
        logger.info("Opens for player with the given media $media. ID: $id")
        val post = KodiPostData(id, PlayerMethod.OPEN, OpenPlayerParam(media, options))
        return makeCall(post)
    }

    /**
     * Pauses or unpause playback and returns the new state
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be played/paused
     *
     * @return the resulting Player Speed
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun playPause(id: KodiID, playerID: PlayerID, state: Boolean? = null): PlayPauseSpeedRespond {
        logger.info("Play/Pause $state Player: $playerID. ID: $id")
//        val post = KodiPostData(id, PlayerMethod.PLAY_PAUSE, PlayPauseParam(playerID, state))
        val post = KodiPostData(id, PlayerMethod.PLAY_PAUSE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (state == null) KodiStringProperty("play", GlobalToggle.TOGGLE.toString()) else KodiBoolProperty("play", state))))
        return makeCall(post)
    }

    /**
     * Stops playback
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be stopped
     *
     * @return OK if everything fine, else error
     */
    @Throws(KodiException::class)
    fun stopPlayer(id: KodiID, playerID: PlayerID): KodiStringRespond {
        logger.info("Stop Player: $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.STOP, KodiIntParam("playerid", playerID.id))
        return makeCall(post)
    }

    /**
     * Returns all active players
     *
     * @param id the ID which should be used for the call
     *
     * @return a list with all active players and type of played media
     */
    @Throws(KodiException::class)
    fun getActivePlayers(id: KodiID): PlayersRespond {
        logger.info("Get all active players. ID: $id")
        val post = KodiPostData(id, PlayerMethod.GET_ACTIVE_PLAYER, KodiEmptyParam())
        return makeCall(post)
    }

    /**
     * Get a list of available players
     *
     * @param id the ID which should be used for the call
     * @param playerType the media of the player which should be returned. null for all.
     *
     * @return a list with all available players
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getPlayers(id: KodiID, playerType: PlayerType? = null): PlayersRespond {
        logger.info("Get all players. ID: $id")
        val post = KodiPostData(id, PlayerMethod.GET_PLAYERS, KodiStringParam("media", if (playerType == null) "all" else "$playerType"))
        return makeCall(post)
    }

    /**
     * Retrieves the currently played item
     *
     * @param id the ID which should be used for the call
     * @param properties a list of all Properties which should be returned. Use ListFieldsAll.getAllFields() to get all items
     *
     * @return the currently played item, if nothing is played, the item will still be returned but is empty
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getPlayerItem(id: KodiID, playerID: PlayerID, properties: List<ListFieldsAll> = listOf()): PlayerItemRespond {
        logger.info("Get Item for Player $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.GET_ITEM, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id), KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Retrieves the values of the given properties
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param properties the properties which should be retrieved. use PlayerPropertyName.getAllFields() to get all
     *
     * @return the player properties
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getPlayerProperties(id: KodiID, playerID: PlayerID, properties: List<PlayerPropertyName> = listOf()): PlayerPropertiesRespond {
        logger.info("Get Properties for Player $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.GET_PROPERTIES, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id), KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Go to previous/next item in the playlist
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param nextItem next or previous item
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun goTo(id: KodiID, playerID: PlayerID, nextItem: GlobalNxtPrev = GlobalNxtPrev.NEXT): KodiStringRespond {
        return goTo(id, playerID, NxtPrevGoTo(nextItem))
    }

    /**
     * Go to specific item in the playlist
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param playlistPos playlistitem to go to
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun goTo(id: KodiID, playerID: PlayerID, playlistPos: PlaylistPosition): KodiStringRespond {
        return goTo(id, playerID, PlaylistPosGoTo(playlistPos))
    }

    /**
     * Go to previous/next/specific item in the playlist
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param nextItem the next item, can either be a playlist position or next/previous
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun goTo(id: KodiID, playerID: PlayerID, nextItem: AbstractGoTo): KodiStringRespond {
        logger.info("Go To: $nextItem for Player $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.GO_TO, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (nextItem is NxtPrevGoTo) KodiStringProperty("to", nextItem.getValue()) else KodiIntProperty("to", nextItem.getValue().toInt()))))
        return makeCall(post)
    }

    /**
     * If picture is zoomed move viewport left/right/up/down otherwise if no zoom is applied call next/previous
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param direction the direction to move
     *
     * @return OK if moved or skipped, else error
     */
    @Throws(KodiException::class)
    fun move(id: KodiID, playerID: PlayerID, direction: GlobalDirection): KodiStringRespond {
        logger.info("Move Player $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.MOVE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id), KodiStringProperty("direction", direction.toString()))))
        return makeCall(post)
    }

    /**
     * Rotates current picture
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param direction clock or counter clockwise
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun rotatePicture(id: KodiID, playerID: PlayerID, direction: GlobalRotate = GlobalRotate.CLOCKWISE): KodiStringRespond {
        logger.info("Rotate Picture clockwise $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.ROTATE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id), KodiStringProperty("value", direction.toString()))))
        return makeCall(post)
    }

    /**
     * Seek through the playing item
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param seekTo seeks to the given time
     *
     * @return current position in percentage, absolute, and total time
     */
    @Throws(KodiException::class)
    fun seek(id: KodiID, playerID: PlayerID, seekTo: PlayerPositionTime): SeekToRespond {
        return seek(id, playerID, SeekToTime(seekTo))
    }

    /**
     * Seek through the playing item
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param seekTo seeks to the given percentage
     *
     * @return current position in percentage, absolute, and total time
     */
    @Throws(KodiException::class)
    fun seek(id: KodiID, playerID: PlayerID, seekTo: PlayerPositionPercentage): SeekToRespond {
        return seek(id, playerID, SeekToPercentage(seekTo))
    }

    /**
     * Seek through the playing item
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param seekTo makes a predefined small or big jump
     *
     * @return current position in percentage, absolute, and total time
     */
    @Throws(KodiException::class)
    fun seek(id: KodiID, playerID: PlayerID, seekTo: PlayerForwBackw): SeekToRespond {
        return seek(id, playerID, SeekBackForward(seekTo))
    }

    /**
     * Seek through the playing item
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param seekTo seeks to the given percentage, time, or predefined jump
     *
     * @return current position in percentage, absolute, and total time
     */
    @Throws(KodiException::class)
    private fun seek(id: KodiID, playerID: PlayerID, seekTo: AbstractSeekTo): SeekToRespond {
        logger.info("Seek to $seekTo for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SEEK, SeekToParam(playerID, seekTo))
        return makeCall(post)
    }

    /**
     * Set the audio stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param nxtPrev choose next or previous stream
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setAudioStream(id: KodiID, playerID: PlayerID, nxtPrev: GlobalNxtPrev): KodiStringRespond {
        return setAudioStream(id, playerID, PrevNextStreamSelection(nxtPrev))
    }

    /**
     * Set the audio stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param streamIndex the stream index
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setAudioStream(id: KodiID, playerID: PlayerID, streamIndex: Int): KodiStringRespond {
        return setAudioStream(id, playerID, StreamIndexSelection(streamIndex))
    }

    /**
     * Set the audio stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param selection sets a specific stream index or prev/next. Use AbstractStreamSelection.get...() to get a specific instance
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun setAudioStream(id: KodiID, playerID: PlayerID, selection: AbstractStreamSelection): KodiStringRespond {
        logger.info("Select Audio stream $selection for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_AUDIO_STREAM, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (selection is StreamIndexSelection) KodiIntProperty("stream", selection.getValue().toInt()) else KodiStringProperty("stream", selection.getValue()))))
        return makeCall(post)
    }

    /**
     * Turn partymode on or off
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param state turn party mode on or off, or null for toggle
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setPartyMode(id: KodiID, playerID: PlayerID, state: Boolean? = null): KodiStringRespond {
        logger.info("Set Party Mode for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_PARTY_MODE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (state == null) KodiStringProperty("partymode", GlobalToggle.TOGGLE.toString()) else KodiBoolProperty("partymode", state))))
        return makeCall(post)
    }

    /**
     * Turn partymode on or off
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param state repeat state, set null to cycle
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setRepeat(id: KodiID, playerID: PlayerID, state: PlayerRepeat? = null): KodiStringRespond {
        logger.info("Set Repeat $state for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_REPEAT, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id), KodiStringProperty("repeat", state?.toString()
                ?: "cycle"))))
        return makeCall(post)
    }

    /**
     * Turn partymode on or off
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param state turn shuffle on or off, or null for toggle
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setShuffle(id: KodiID, playerID: PlayerID, state: Boolean? = null): KodiStringRespond {
        logger.info("Set Shuffle $state for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_SHUFFLE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (state == null) KodiStringProperty("shuffle", GlobalToggle.TOGGLE.toString()) else KodiBoolProperty("shuffle", state))))
        return makeCall(post)
    }

    /**
     * Set the speed of the current playback
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param speed the Speed. Can bei either -32 ... 32. Use PlayerSpeed static params as speed levels
     *
     * @return the new PlayerSpeed
     */
    @Throws(KodiException::class)
    fun setSpeed(id: KodiID, playerID: PlayerID, speed: PlayerSpeed): PlayPauseSpeedRespond {
        return setSpeed(id, playerID, SpeedLevel(speed))
    }

    /**
     * Set the speed of the current playback
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param incrDecr increment/decrement Speed Level
     *
     * @return the new PlayerSpeed
     */
    @Throws(KodiException::class)
    fun setSpeed(id: KodiID, playerID: PlayerID, incrDecr: GlobalIncrementDecrement): PlayPauseSpeedRespond {
        return setSpeed(id, playerID, IncrDecrSpeed(incrDecr))
    }

    /**
     * Set the speed of the current playback
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param speed the Speed. Can bei either -32 ... 32 or increment/decrement. Use AbstractSpeed.get...() to get a specific instance
     *
     * @return the new PlayerSpeed
     */
    @Throws(KodiException::class)
    private fun setSpeed(id: KodiID, playerID: PlayerID, speed: AbstractSpeed): PlayPauseSpeedRespond {
        logger.info("Set Speed $speed for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_SPEED, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (speed is SpeedLevel) KodiIntProperty("speed", speed.getValue().toInt()) else KodiStringProperty("speed", speed.getValue()))))
        return makeCall(post)
    }

    /**
     * Set the subtitle displayed by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param subIndex the index of a subtitle
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setSubtitle(id: KodiID, playerID: PlayerID, subIndex: Int): KodiStringRespond {
        return setSubtitle(id, playerID, SubtitleIndexSelection(subIndex))
    }

    /**
     * Set the subtitle displayed by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param nxtPrevOnOff next/prev/on/off the subtitle
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setSubtitle(id: KodiID, playerID: PlayerID, nxtPrevOnOff: SubtitleOperation): KodiStringRespond {
        return setSubtitle(id, playerID, SubtitleOperationSelection(nxtPrevOnOff))
    }

    /**
     * Set the subtitle displayed by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param subtitle can either be the index of a subtitle, or next/prev/on/off. Use AbstractSubtitleSelection.get...() to get a specific instance
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun setSubtitle(id: KodiID, playerID: PlayerID, subtitle: AbstractSubtitleSelection): KodiStringRespond {
        logger.info("Set Subtitle $subtitle for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_SUBTITLE, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (subtitle is SubtitleIndexSelection) KodiIntProperty("subtitle", subtitle.getValue().toInt()) else KodiStringProperty("subtitle", subtitle.getValue()))))
        return makeCall(post)
    }

    /**
     * Set the video stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param nxtPrev choose next or previous stream
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setVideoStream(id: KodiID, playerID: PlayerID, nxtPrev: GlobalNxtPrev): KodiStringRespond {
        return setVideoStream(id, playerID, PrevNextStreamSelection(nxtPrev))
    }

    /**
     * Set the video stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param streamIndex the stream index
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setVideoStream(id: KodiID, playerID: PlayerID, streamIndex: Int): KodiStringRespond {
        return setVideoStream(id, playerID, StreamIndexSelection(streamIndex))
    }

    /**
     * Set the video stream played by the player
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param selection sets a specific stream index or prev/next. Use AbstractStreamSelection.get...() to get a specific instance
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun setVideoStream(id: KodiID, playerID: PlayerID, selection: AbstractStreamSelection): KodiStringRespond {
        logger.info("Select Video stream $selection for $playerID. ID: $id")
        val post = KodiPostData(id, PlayerMethod.SET_VIDEO_STREAM, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (selection is StreamIndexSelection) KodiIntProperty("stream", selection.getValue().toInt()) else KodiStringProperty("stream", selection.getValue()))))
        return makeCall(post)
    }

    /**
     * Zoom current picture
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param zoom zoom level, must be between 1 and 10
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun zoomPicture(id: KodiID, playerID: PlayerID, zoom: Int): KodiStringRespond {
        return zoomPicture(id, playerID, ZoomLevel(zoom))
    }

    /**
     * Zoom current picture
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param inOut zooms in or out
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun zoomPicture(id: KodiID, playerID: PlayerID, inOut: PlayerZoomOperation): KodiStringRespond {
        return zoomPicture(id, playerID, ZoomOperation(inOut))
    }

    /**
     * Zoom current picture
     *
     * @param id the ID which should be used for the call
     * @param playerID the ID of the player which should be used
     * @param zoom zoom level, can be either a level between 1 and 10, or a static step in/out. Use AbstractZoom.get...() to get a specific instance
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun zoomPicture(id: KodiID, playerID: PlayerID, zoom: AbstractZoom): KodiStringRespond {
        logger.info("Zoom to $zoom for $playerID. ID: $id")
        if (!zoom.isValid())
            throw IllegalArgumentException("Zoom Level must be between 1 and 10.")
        val post = KodiPostData(id, PlayerMethod.ZOOM, KodiPropertiesParam(listOf(KodiIntProperty("playerid", playerID.id),
                if (zoom is ZoomLevel) KodiIntProperty("zoom", zoom.getValue().toInt()) else KodiStringProperty("zoom", zoom.getValue()))))
        return makeCall(post)
    }
}