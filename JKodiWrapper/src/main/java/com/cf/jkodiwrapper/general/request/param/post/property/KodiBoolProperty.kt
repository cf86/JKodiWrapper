package com.cf.jkodiwrapper.general.request.param.post.property

data class KodiBoolProperty(var key: String, var value: Boolean) : KodiProperty() {

    override fun toJSON(): String {
        return "\"$key\":$value"
    }
}