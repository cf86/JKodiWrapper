package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.audio.params.entity.filter.song.AbstractSongFilter
import com.cf.jkodiwrapper.types.audio.AudioFieldsSong
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class GetSongsParam(var properties: List<AudioFieldsSong>,
                         var filter: AbstractSongFilter?,
                         var limits: ListLimits,
                         var sort: ListSort,
                         var includeSingles: Boolean,
                         var allRoles: Boolean) : KodiPostParam() {

    override fun toJSON(): String {
        var result = "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}],\"includesingles\":$includeSingles,\"allroles\":$allRoles," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"

        if (filter != null)
            result += ",\"filter\":{${filter?.toJSON()}}"

        return result
    }
}