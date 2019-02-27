package com.cf.jkodiwrapper.types.addon

enum class AddonFields(var fieldName: String) {

    AUTHOR("author"),
    BROKEN("broken"),
    DEPENDENCIES("dependencies"),
    DESCRIPTION("description"),
    DISCLAIMER("disclaimer"),
    ENABLED("enabled"),
    EXTRAINFO("extrainfo"),
    FANART("fanart"),
    INSTALLED("installed"),
    NAME("name"),
    PATH("path"),
    RATING("rating"),
    SUMMARY("summary"),
    THUMBNAIL("thumbnail"),
    VERSION("version");

    override fun toString(): String {
        return fieldName
    }

    companion object {
        @JvmStatic
        fun getAllAddonFields(): List<AddonFields> {
            return AddonFields.values().asList()
        }
    }
}