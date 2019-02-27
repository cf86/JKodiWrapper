package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiProperty

open class KodiPropertiesParam(var props: List<KodiProperty>) : KodiPostParam() {

    override fun toJSON(): String {
        return props.joinToString(",") { it.toJSON() }
    }

}