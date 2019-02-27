package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.KodiPostParam

class KodiEmptyParam : KodiPostParam() {

    override fun toJSON(): String {
        return ""
    }

}