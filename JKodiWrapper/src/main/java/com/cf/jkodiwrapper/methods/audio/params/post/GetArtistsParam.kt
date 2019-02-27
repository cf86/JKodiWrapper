package com.cf.jkodiwrapper.methods.audio.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.audio.params.entity.filter.artist.AbstractArtistFilter
import com.cf.jkodiwrapper.types.audio.AudioFieldsArtist
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort

data class GetArtistsParam(var properties: List<AudioFieldsArtist>,
                           var filter: AbstractArtistFilter?,
                           var limits: ListLimits,
                           var sort: ListSort,
                           var albumArtistsOnly: Boolean,
                           var allRoles: Boolean) : KodiPostParam() {

    override fun toJSON(): String {
        var result = "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}],\"albumartistsonly\":$albumArtistsOnly,\"allroles\":$allRoles," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order}\"}"

        if (filter != null)
            result += ",\"filter\":{${filter?.toJSON()}}"

        return result
    }
}