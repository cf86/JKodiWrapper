package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty

data class KodiStringArrParam(var key: String,
                              var array: List<String>) : KodiPropertiesParam(listOf(KodiListProperty(key, array)))

/*
data class KodiStringArrParam(var key: String,
                              var array: List<String>) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"$key\":[${array.joinToString(",") { "\"$it\"" }}]"
    }

}
 */