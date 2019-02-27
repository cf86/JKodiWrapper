package com.cf.jkodiwrapper.types.gui

enum class GUIPropertyName(var propName: String) {

    CURRENTCONTROL("currentcontrol"),
    CURRENTWINDOW("currentwindow"),
    FULLSCREEN("fullscreen"),
    SKIN("skin"),
    STEREOSCOPICMODE("stereoscopicmode");

    override fun toString(): String {
        return propName
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<GUIPropertyName> {
            return GUIPropertyName.values().asList()
        }
    }
}