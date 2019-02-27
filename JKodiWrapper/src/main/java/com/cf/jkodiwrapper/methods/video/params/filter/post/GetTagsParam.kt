package com.cf.jkodiwrapper.methods.video.params.filter.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.library.LibraryFieldsTag
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class GetTagsParam(var properties: List<LibraryFieldsTag>,
                        var limits: ListLimits,
                        var sort: ListSort) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"type\":\"movie\",\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"
    }
}