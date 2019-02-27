package com.cf.jkodiwrapper.general.request.param.post.property

data class KodiNullProperty(var key: String) : KodiProperty() {

    override fun toJSON(): String {
        return "\"$key\":null"
    }
}