package com.cf.jkodiwrapper.methods.profiles

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class ProfilesMethod(var methodName: String) : KodiMethod {

    GET_CURRENT_PROFILE("Profiles.GetCurrentProfile"),
    GET_PROFILES("Profiles.GetProfiles"),
    LOAD_PROFILE("Profiles.LoadProfile");

    override fun toString(): String {
        return methodName
    }
}