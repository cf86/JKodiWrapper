package com.cf.jkodiwrapper.methods.addons.params.entity

data class AddonURLParam(var url: String) : AbstractAddonParam() {

    override fun toJSON(): String {
        return "\"params\":\"$url\""
    }
}