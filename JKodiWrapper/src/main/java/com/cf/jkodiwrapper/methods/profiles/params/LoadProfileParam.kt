package com.cf.jkodiwrapper.methods.profiles.params

import com.cf.jkodiwrapper.general.request.KodiPostParam
import com.cf.jkodiwrapper.types.profiles.ProfilePassword

data class LoadProfileParam(var profile: String,
                            var prompt: Boolean,
                            var password: ProfilePassword) : KodiPostParam() {

    override fun toJSON(): String {
        return "\"profile\":\"$profile\",\"prompt\":$prompt,\"password\":{\"encryption\":\"${password.encryption}\",\"value\":\"${password.value}\"}"
    }
}