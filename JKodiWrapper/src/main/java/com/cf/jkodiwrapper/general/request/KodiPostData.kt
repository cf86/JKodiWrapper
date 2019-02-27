package com.cf.jkodiwrapper.general.request

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.methods.KodiMethod
import com.cf.jkodiwrapper.general.request.param.post.KodiEmptyParam

data class KodiPostData(var id: KodiID = KodiID(1),
                        var method: KodiMethod,
                        var kodiPostParam: KodiPostParam = KodiEmptyParam(),
                        var jsonrpc: String = "2.0") {

    fun toJsonString(): String {
        return "{\"jsonrpc\":\"$jsonrpc\",\"id\":$id,\"method\":\"$method\", \"params\": {${kodiPostParam.toJSON()}}}"
    }
}