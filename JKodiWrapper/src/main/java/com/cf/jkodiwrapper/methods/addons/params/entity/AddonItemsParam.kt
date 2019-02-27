package com.cf.jkodiwrapper.methods.addons.params.entity

data class AddonItemsParam(var items: List<String>) : AbstractAddonParam() {

    override fun toJSON(): String {
        return "\"params\":[${items.joinToString(",") { "\"$it\"" }}]"
    }
}