package com.cf.jkodiwrapper.methods.application

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class ApplicationMethod(var methodName: String) : KodiMethod {

    GET_PROPERTIES("Application.GetProperties"),
    QUIT("Application.Quit"),
    SET_MUTE("Application.SetMute"),
    SET_VOLUME("Application.SetVolume");

    override fun toString(): String {
        return methodName
    }
}