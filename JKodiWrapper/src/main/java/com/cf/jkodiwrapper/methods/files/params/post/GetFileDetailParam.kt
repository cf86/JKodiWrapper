package com.cf.jkodiwrapper.methods.files.params.post

import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.list.ListFieldsFiles

data class GetFileDetailParam(var directory: KodiPath,
                              var media: FilesMedia,
                              var properties: List<ListFieldsFiles>) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"file\": \"$directory\",\"media\":\"${media.media}\",\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]"
    }
}