package com.cf.jkodiwrapper.types.gui

enum class GUINotificationImage(var image: String) {

    INFO("info"),
    WARNING("warning"),
    ERROR("error");

    override fun toString(): String {
        return image
    }
}