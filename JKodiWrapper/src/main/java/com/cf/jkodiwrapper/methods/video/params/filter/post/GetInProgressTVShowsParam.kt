package com.cf.jkodiwrapper.methods.video.params.filter.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.video.VideoFieldsTVShow

data class GetInProgressTVShowsParam(var properties: List<VideoFieldsTVShow>,
                                     var limits: ListLimits,
                                     var sort: ListSort) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"
    }
}