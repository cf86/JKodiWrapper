package com.cf.jkodiwrapper.methods.files.params.post

import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.video.VideoResume

data class SetFileDetailsParam(var file: KodiPath,
                               var mediaType: FilesMedia,
                               var playcount: Int?,
                               var lastplayed: String?,
                               var resume: VideoResume) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"file\":\"$file\",\"media\":\"${mediaType.media}\",\"playcount\":${if (playcount == null) "null" else "\"$playcount\""}," +
                "\"lastplayed\":${if (lastplayed == null) "null" else "\"$lastplayed\",\"resume\":{\"position\":${resume.position},\"total\":${resume.total}}"}"
    }
}