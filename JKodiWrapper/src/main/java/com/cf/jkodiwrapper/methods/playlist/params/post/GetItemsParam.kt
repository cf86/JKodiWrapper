package com.cf.jkodiwrapper.methods.playlist.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.list.ListFieldsAll
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.playlist.PlaylistID

data class GetItemsParam(var playlistID: PlaylistID,
                         var properties: List<ListFieldsAll>,
                         var limits: ListLimits,
                         var sort: ListSort) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"playlistid\":$playlistID,\"properties\":[${properties.joinToString(",") { "\"$it\"" }}]," +
                "\"limits\":{\"start\":${limits.start},\"end\":${limits.end}},\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order.order}\"}"
    }
}