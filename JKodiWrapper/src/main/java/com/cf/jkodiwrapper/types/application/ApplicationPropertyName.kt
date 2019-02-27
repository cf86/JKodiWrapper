package com.cf.jkodiwrapper.types.application

enum class ApplicationPropertyName(var appName: String) {

    VOLUME("volume"),
    MUTED("muted"),
    NAME("name"),
    VERSION("version");

    override fun toString(): String {
        return appName
    }
}