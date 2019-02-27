package com.cf.jkodiwrapper.methods.video.params.filter.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.video.params.filter.entity.episode.AbstractEpisodesFilter
import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.video.VideoFieldsEpisode

data class GetEpisodesParam(var tvShowID: LibraryID,
                            var season: Int,
                            var properties: List<VideoFieldsEpisode>,
                            var limits: ListLimits,
                            var sort: ListSort,
                            var filter: AbstractEpisodesFilter?) : KodiPostParam() {

    override fun toJSON(): String {
        var result = "\"tvshowid\":$tvShowID,\"season\":$season,\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"

        if (filter != null)
            result += ",\"filter\":{${filter?.toJSON()}}"

        return result
    }
}