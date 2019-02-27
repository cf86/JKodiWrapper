package com.cf.jkodiwrapper.methods.addons.params.entity

data class AddonMapParam(var params: Map<String, String>) : AbstractAddonParam() {

    override fun toJSON(): String {
        return "\"params\":{${params.entries.joinToString(",") { "\"${it.key}\":\"${it.value}\"" }}}"
    }
}