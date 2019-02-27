package com.cf.jkodiwrapper.methods.files.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class SourceParam(var media: FilesMedia,
                       var sorting: ListSort,
                       var limits: ListLimits) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"media\":\"${media.media}\",\"limits\":{\"start\":${limits.start},\"end\":${limits.end}},\"sort\":{\"ignorearticle\":${sorting.ignorearticle},\"method\":\"${sorting.method}\",\"order\":\"${sorting.order.order}\"}"
    }
}