package com.cf.jkodiwrapper.general.request.param.post.property

data class KodiIntProperty(var key: String, var value: Int) : KodiProperty() {

    override fun toJSON(): String {
        return "\"$key\":$value"
    }
}