package com.cf.jkodiwrapper.methods.addons.params.post

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.methods.addons.params.entity.AbstractAddonParam
import com.cf.jkodiwrapper.types.addon.AddonID

data class ExecAddonParam(var addonID: AddonID,
                          var additionalParams: AbstractAddonParam,
                          var wait: Boolean) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"addonid\":\"$addonID\",\"wait\":$wait,${additionalParams.toJSON()}"
    }
}