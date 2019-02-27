package com.cf.jkodiwrapper.methods.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiBoolParam
import com.cf.jkodiwrapper.general.request.param.post.KodiIntParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringArrParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringParam
import com.cf.jkodiwrapper.general.respond.KodiBoolRespond
import com.cf.jkodiwrapper.general.respond.KodiIntRespond
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.application.params.entity.volume.AbstractVolume
import com.cf.jkodiwrapper.methods.application.params.entity.volume.IncrDecrVolume
import com.cf.jkodiwrapper.methods.application.params.entity.volume.LevelVolume
import com.cf.jkodiwrapper.methods.application.respond.PropertiesRespond
import com.cf.jkodiwrapper.types.application.ApplicationPropertyName
import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement
import com.cf.jkodiwrapper.types.global.GlobalToggle
import org.slf4j.LoggerFactory

class KodiApplication @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Quit application
     *
     * @param id the ID which should be used for the call
     *
     * @return Respond which contains an error, or OK as a result if Kodi was quited successfully
     */
    @Throws(KodiException::class)
    fun quit(id: KodiID): KodiStringRespond {
        logger.info("Quit Application. ID: $id")
        val post = KodiPostData(id, ApplicationMethod.QUIT)
        return makeCall(post)
    }

    /**
     * Retrieves the values of the given properties
     *
     * @param id the ID which should be used for the call
     *
     * @return Respond which contains an error, or as a result the KodiProperties
     */
    @Throws(KodiException::class)
    fun getProperties(id: KodiID): PropertiesRespond {
        logger.info("Get Application Properties. ID: $id")
        val post = KodiPostData(id, ApplicationMethod.GET_PROPERTIES, KodiStringArrParam("properties",
                listOf(ApplicationPropertyName.MUTED.toString(), ApplicationPropertyName.NAME.toString(), ApplicationPropertyName.VERSION.toString(), ApplicationPropertyName.VOLUME.toString())))
        return makeCall(post)
    }

    /**
     * Toggle mute/unmute
     *
     * @param id the ID which should be used for the call
     * @param muteState true for mute, false to un-mute, null to toggle
     *
     * @return Respond which contains an error, or the current set mute state as a result
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setMute(id: KodiID, muteState: Boolean? = null): KodiBoolRespond {
        logger.info("Set Mute: $muteState. ID: $id")
        val post = KodiPostData(id, ApplicationMethod.SET_MUTE, if (muteState == null) KodiStringParam("mute", GlobalToggle.TOGGLE.toString()) else KodiBoolParam("mute", muteState))
        return makeCall(post)
    }

    /**
     * Set the current volume
     *
     * @param id the ID which should be used for the call
     * @param volume the given value, must be between 0 and 100
     *
     * @return Respond which contains an error, or the current set volume
     */
    @Throws(KodiException::class)
    fun setVolume(id: KodiID, volume: Int): KodiIntRespond {
        return setVolume(id, LevelVolume(volume))
    }

    /**
     * Increases or Decreses the Volume by a fixed value (default is 3)
     *
     * @param id the ID which should be used for the call
     * @param incrDecr increase to increase, decrease to decrease the volume by a fixed value
     *
     * @return Respond which contains an error, or the current set volume
     */
    @Throws(KodiException::class)
    fun setVolume(id: KodiID, incrDecr: GlobalIncrementDecrement): KodiIntRespond {
        return setVolume(id, IncrDecrVolume(incrDecr))
    }

    /**
     * Sets the volume of the application to the specific value
     *
     * @param id the ID which should be used for the call
     * @param volume the volume to set. Must be between 0 and 100 or increment/decrement. Use AbstractVolume.get...() to get the specific instances
     *
     * @return Respond which contains an error, or the current set volume
     */
    @Throws(KodiException::class)
    private fun setVolume(id: KodiID, volume: AbstractVolume): KodiIntRespond {
        logger.info("Set Application Volume. ID: $id")
        if (!volume.isValid())
            throw KodiException("Volume must be between 0 and 100.")

        val post = KodiPostData(id, ApplicationMethod.SET_VOLUME, if (volume is LevelVolume) KodiIntParam("volume", volume.getValue().toInt())
        else KodiStringParam("volume", volume.getValue()))
        return makeCall(post)
    }
}