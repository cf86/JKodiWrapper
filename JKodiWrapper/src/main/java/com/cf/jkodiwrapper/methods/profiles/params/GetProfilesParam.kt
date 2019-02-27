package com.cf.jkodiwrapper.methods.profiles.params

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.profiles.ProfilesFieldsProfile

data class GetProfilesParam(var properties: List<ProfilesFieldsProfile>,
                            var limits: ListLimits,
                            var sort: ListSort) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}],\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"sort\":{\"ignorearticle\":${sort.ignorearticle},\"method\":\"${sort.method}\",\"order\":\"${sort.order.order}\"}"
    }
}