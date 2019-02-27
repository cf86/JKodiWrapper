package com.cf.jkodiwrapper.methods.system

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiStringArrParam
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.system.respond.PropertiesRespond
import com.cf.jkodiwrapper.types.system.SystemPropertyName
import org.slf4j.LoggerFactory

class KodiSystem @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Ejects or closes the optical disc drive (if available)
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun ejectOpticalDrive(id: KodiID): KodiStringRespond {
        logger.info("Eject Optical Drive. ID: $id")
        val post = KodiPostData(id, SystemMethod.EJECT_OPTICAL_DRIVE)
        return makeCall(post)
    }

    /**
     * Retrieves the values of the given properties
     *
     * @param id the ID which should be used for the call
     * @param properties which properties should be returned. Use SystemPropertyName.getAllFields() to get all fields
     *
     * @return the properties
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getProperties(id: KodiID, properties: List<SystemPropertyName> = listOf()): PropertiesRespond {
        logger.info("Get System Properties. ID: $id")
        val post = KodiPostData(id, SystemMethod.GET_PROPERTIES, KodiStringArrParam("properties", properties.map { it.property }))
        return makeCall(post)
    }

    /**
     * Puts the system running Kodi into hibernate mode
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun hibernate(id: KodiID): KodiStringRespond {
        logger.info("Hibernates the System. ID: $id")
        val post = KodiPostData(id, SystemMethod.HIBERNATE)
        return makeCall(post)
    }

    /**
     * Reboots the system running Kodi
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun reboot(id: KodiID): KodiStringRespond {
        logger.info("Reboots the System. ID: $id")
        val post = KodiPostData(id, SystemMethod.REBOOT)
        return makeCall(post)
    }

    /**
     * Shuts the system running Kodi down
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun shutdown(id: KodiID): KodiStringRespond {
        logger.info("Shuts the System down. ID: $id")
        val post = KodiPostData(id, SystemMethod.SHUTDOWN)
        return makeCall(post)
    }

    /**
     * Suspends the system running Kodi
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun suspend(id: KodiID): KodiStringRespond {
        logger.info("Suspends the System. ID: $id")
        val post = KodiPostData(id, SystemMethod.SUSPEND)
        return makeCall(post)
    }
}