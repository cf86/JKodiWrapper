package com.cf.jkodiwrapper.methods.system

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class SystemMethod(var methodName: String) : KodiMethod {

    EJECT_OPTICAL_DRIVE("System.EjectOpticalDrive"),
    GET_PROPERTIES("System.GetProperties"),
    HIBERNATE("System.Hibernate"),
    REBOOT("System.Reboot"),
    SHUTDOWN("System.Shutdown"),
    SUSPEND("System.Suspend");

    override fun toString(): String {
        return methodName
    }
}