package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.audio.AudioFieldsSong
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class GetRecPlayedSongsParam(var properties: List<AudioFieldsSong>,
                                  var limits: ListLimits,
                                  var sort: ListSort) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"
    }
}