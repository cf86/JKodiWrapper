package com.cf.jkodiwrapper.methods.gui

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class GUIMethod(var methodName: String) : KodiMethod {

    ACTIVATE_WINDOW("GUI.ActivateWindow"),
    GET_PROPERTIES("GUI.GetProperties"),
    GET_STEREOSCOPIC_MODES("GUI.GetStereoscopicModes"),
    SET_FULLSCREEN("GUI.SetFullscreen"),
    SET_STEREOSCOPIC_MODE("GUI.SetStereoscopicMode"),
    SHOW_NOTIFICATION("GUI.ShowNotification");


    override fun toString(): String {
        return methodName
    }
}