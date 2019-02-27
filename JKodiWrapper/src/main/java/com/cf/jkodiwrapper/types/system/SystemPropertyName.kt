package com.cf.jkodiwrapper.types.system

enum class SystemPropertyName(var property: String) {

    CAN_SHUTDOWN("canshutdown"),
    CAN_SUSPEND("cansuspend"),
    CAN_HIBERNATE("canhibernate"),
    CAN_REBOOT("canreboot");

    override fun toString(): String {
        return property
    }

    companion object {
        @JvmStatic
        fun getAllFields(): List<SystemPropertyName> {
            return SystemPropertyName.values().asList()
        }
    }
}