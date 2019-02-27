package com.cf.jkodiwrapper.methods.player

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class PlayerMethod(var methodName: String) : KodiMethod {

    GET_ACTIVE_PLAYER("Player.GetActivePlayers"),
    GET_ITEM("Player.getItem"),
    GET_PLAYERS("Player.GetPlayers"),
    GET_PROPERTIES("Player.GetProperties"),
    GO_TO("Player.goTo"),
    MOVE("Player.Move"),
    OPEN("Player.Open"),
    PLAY_PAUSE("Player.PlayPause"),
    ROTATE("Player.Rotate"),
    SEEK("Player.Seek"),
    SET_AUDIO_STREAM("Player.SetAudioStream"),
    SET_PARTY_MODE("Player.SetPartymode"),
    SET_REPEAT("Player.SetRepeat"),
    SET_SHUFFLE("Player.SetShuffle"),
    SET_SPEED("Player.SetSpeed"),
    SET_SUBTITLE("Player.SetSubtitle"),
    SET_VIDEO_STREAM("Player.SetVideoStream"),
    STOP("Player.Stop"),
    ZOOM("Player.Zoom");

    override fun toString(): String {
        return methodName
    }
}