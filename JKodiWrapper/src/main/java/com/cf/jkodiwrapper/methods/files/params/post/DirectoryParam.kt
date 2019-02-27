package com.cf.jkodiwrapper.methods.files.params.post

import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.list.ListFieldsFiles
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

class DirectoryParam(var directory: KodiPath,
                     var media: FilesMedia,
                     var properties: List<ListFieldsFiles>,
                     var sort: ListSort,
                     var limits: ListLimits) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"directory\": \"$directory\",\"media\":\"${media.media}\",\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}},\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order.order}\"}"
    }
}