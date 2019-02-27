package com.cf.jkodiwrapper.methods.addons.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.addon.AddonContent
import com.cf.jkodiwrapper.types.addon.AddonFields
import com.cf.jkodiwrapper.types.addon.AddonTypes
import com.cf.jkodiwrapper.types.list.ListLimits

data class GetAttributeParam(var type: AddonTypes,
                             var content: AddonContent,
                             var enabled: Boolean?,
                             var properties: List<AddonFields>,
                             var limits: ListLimits,
                             var installed: Boolean?) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"type\":\"${type.type}\",\"content\":\"${content.content}\",\"enabled\":${if (enabled == null) "\"all\"" else "$enabled"}," +
                "\"properties\":[${properties.joinToString(",") { "\"$it\"" }}],\"limits\":{\"start\":${limits.start},\"end\":${limits.end}}," +
                "\"installed\":${if (installed == null) "\"all\"" else "$installed"}"
    }

}