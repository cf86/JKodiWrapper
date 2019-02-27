package com.cf.jkodiwrapper.types.profiles

enum class ProfilesFieldsProfile(var field: String) {

    THUMBNAIL("thumbnail"),
    LOCKMODE("lockmode");

    override fun toString(): String {
        return field
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<ProfilesFieldsProfile> {
            return ProfilesFieldsProfile.values().asList()
        }
    }
}