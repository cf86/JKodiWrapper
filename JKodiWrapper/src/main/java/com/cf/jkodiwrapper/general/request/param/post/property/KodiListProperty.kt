package com.cf.jkodiwrapper.general.request.param.post.property

data class KodiListProperty(var key: String, var value: List<Any>) : KodiProperty() {

    override fun toJSON(): String {
        return "\"$key\":[${value.joinToString(",") { "\"$it\"" }}]"
    }
}