package com.cf.jkodiwrapper.methods.gui

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiBoolParam
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiBoolRespond
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.gui.respond.PropertiesRespond
import com.cf.jkodiwrapper.methods.gui.respond.StereoscopicModesRespond
import com.cf.jkodiwrapper.types.global.GlobalToggle
import com.cf.jkodiwrapper.types.gui.GUINotificationImage
import com.cf.jkodiwrapper.types.gui.GUIPropertyName
import com.cf.jkodiwrapper.types.gui.GUIWindow
import org.slf4j.LoggerFactory

class KodiGUI @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Activates the given window
     *
     * @param id the ID which should be used for the call
     * @param window the window to activate
     * @param params list of params for the window, this can not be empty
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun activateWindow(id: KodiID, window: GUIWindow, params: List<String> = listOf()): KodiStringRespond {
        logger.info("Activate Window $window with params: $params. ID: $id")
        val post = KodiPostData(id, GUIMethod.ACTIVATE_WINDOW, KodiPropertiesParam(listOf(KodiStringProperty("window", window.toString()),
                KodiListProperty("parameters", params))))
        return makeCall(post)
    }

    /**
     * Activates the given window
     *
     * @param id the ID which should be used for the call
     * @param properties The Properties which should be returned. Use GUIPropertyName.getAllFields() to get all fields
     *
     * @return Result which contains the properties
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getProperties(id: KodiID, properties: List<GUIPropertyName> = listOf()): PropertiesRespond {
        logger.info("Get Properties: $properties. ID: $id")
        val post = KodiPostData(id, GUIMethod.GET_PROPERTIES, KodiPropertiesParam(listOf(KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Returns the supported stereoscopic modes of the GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return a list with all stereoscopic modes
     */
    @Throws(KodiException::class)
    fun getStereoscopicModes(id: KodiID): StereoscopicModesRespond {
        logger.info("Get Stereoscopic modes. ID: $id")
        val post = KodiPostData(id, GUIMethod.GET_STEREOSCOPIC_MODES)
        return makeCall(post)
    }

    /**
     * Toggle fullscreen/GUI
     *
     * @param id the ID which should be used for the call
     * @param state true/false to set Fullscreen or not, null to toggle
     *
     * @return the current Fullscreen state
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setFullscreen(id: KodiID, state: Boolean? = null): KodiBoolRespond {
        logger.info("Set Fullscreen $state. ID: $id")
        val post = KodiPostData(id, GUIMethod.SET_FULLSCREEN, if (state == null) KodiStringParam("fullscreen", GlobalToggle.TOGGLE.toString()) else KodiBoolParam("fullscreen", state))
        return makeCall(post)
    }

    /**
     * Sets the stereoscopic mode of the GUI to the given mode
     *
     * @param id the ID which should be used for the call
     * @param mode the stereoscopic mode to set
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun setStereoscopicMode(id: KodiID, mode: String): KodiStringRespond {
        logger.info("Set Stereoscopic Mode: $mode. ID: $id")
        val post = KodiPostData(id, GUIMethod.SET_STEREOSCOPIC_MODE, KodiStringParam("mode", mode))
        return makeCall(post)
    }

    /**
     * Sets the stereoscopic mode of the GUI to the given mode
     *
     * @param id the ID which should be used for the call
     * @param title the notification title
     * @param image the notification image, null for no image
     * @param displaytime the notification displaytime in ms
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun showNotification(id: KodiID, title: String, message: String, image: GUINotificationImage? = null, displaytime: Int = 5000): KodiStringRespond {
        logger.info("Set Stereoscopic Mode. ID: $id")
        val post = KodiPostData(id, GUIMethod.SHOW_NOTIFICATION, KodiPropertiesParam(listOf(KodiStringProperty("title", title),
                KodiStringProperty("message", message), KodiStringProperty("image", image.toString()), KodiIntProperty("displaytime", displaytime))))
        return makeCall(post)
    }
}