package com.cf.jkodiwrapper.methods.addons

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class AddonsMethod(var methodName: String) : KodiMethod {

    EXECUTE_ADDON("Addons.ExecuteAddon"),
    GET_ADDON_DETAILS("Addons.GetAddonDetails"),
    GET_ADDONS("Addons.GetAddons"),
    SET_ADDON_ENABLED("Addons.SetAddonEnabled");

    override fun toString(): String {
        return methodName
    }
}