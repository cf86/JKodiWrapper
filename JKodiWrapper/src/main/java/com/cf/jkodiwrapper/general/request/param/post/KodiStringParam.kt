package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty

data class KodiStringParam(var key: String,
                           var str: String) : KodiPropertiesParam(listOf(KodiStringProperty(key, str)))


/*
data class KodiStringParam(var key: String,
                           var str: String) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"$key\":\"$str\""
    }

}
 */