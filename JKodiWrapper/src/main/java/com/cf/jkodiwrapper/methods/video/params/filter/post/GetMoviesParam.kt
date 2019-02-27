package com.cf.jkodiwrapper.methods.video.params.filter.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.video.params.filter.entity.movie.AbstractMovieFilter
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.video.VideoFieldsMovie

data class GetMoviesParam(var properties: List<VideoFieldsMovie>,
                          var limits: ListLimits,
                          var sort: ListSort,
                          var filter: AbstractMovieFilter?) : KodiPostParam() {

    override fun toJSON(): String {
        var result = "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"

        if (filter != null)
            result += ",\"filter\":{${filter?.toJSON()}}"

        return result
    }
}