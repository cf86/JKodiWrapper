package com.cf.jkodiwrapper.methods.player.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.player.params.entity.playermedia.AbstractPlayerMedia

class OpenPlayerParam(var media: AbstractPlayerMedia,
                      var options: Map<String, String>?) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"item\":{${media.toJSON()}},\"options\":{${options?.entries?.joinToString(",") { "\"${it.key}\":\"${it.value}\"" }
                ?: ""}}"
    }
}