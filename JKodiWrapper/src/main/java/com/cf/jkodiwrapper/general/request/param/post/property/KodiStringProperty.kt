package com.cf.jkodiwrapper.general.request.param.post.property

data class KodiStringProperty(var key: String, var value: String) : KodiProperty() {

    override fun toJSON(): String {
        return "\"$key\":\"$value\""
    }
}