package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.audio.AudioFieldsSong
import com.cf.jkodiwrapper.types.list.ListAmount
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class GetRecAddedSongsParam(var properties: List<AudioFieldsSong>,
                                 var limits: ListLimits,
                                 var sort: ListSort,
                                 var albumLimit: ListAmount) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}],\"albumlimit\":$albumLimit," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"
    }
}