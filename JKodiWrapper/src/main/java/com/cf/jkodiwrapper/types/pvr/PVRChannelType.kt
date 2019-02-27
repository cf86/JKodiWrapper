package com.cf.jkodiwrapper.types.pvr

enum class PVRChannelType(var fieldName: String) {

    TV("tv"),
    RADIO("radio");

    override fun toString(): String {
        return fieldName
    }
}