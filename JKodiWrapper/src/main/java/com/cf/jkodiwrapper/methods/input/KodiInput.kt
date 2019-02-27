package com.cf.jkodiwrapper.methods.input

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.KodiStringParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.types.input.InputAction
import org.slf4j.LoggerFactory

class KodiInput @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Goes back in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun back(id: KodiID): KodiStringRespond {
        logger.info("Back Pressed. ID: $id")
        val post = KodiPostData(id, InputMethod.BACK)
        return makeCall(post)
    }

    /**
     * Shows the context menu
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun contextMenu(id: KodiID): KodiStringRespond {
        logger.info("Open Context Menu. ID: $id")
        val post = KodiPostData(id, InputMethod.CONTEXT_MENU)
        return makeCall(post)
    }

    /**
     * Navigate down in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun down(id: KodiID): KodiStringRespond {
        logger.info("Down Pressed. ID: $id")
        val post = KodiPostData(id, InputMethod.DOWN)
        return makeCall(post)
    }

    /**
     * Execute a specific action
     *
     * @param id the ID which should be used for the call
     * @param action the action to execute
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun executeAddon(id: KodiID, action: InputAction): KodiStringRespond {
        logger.info("Execute Action $action. ID: $id")
        val post = KodiPostData(id, InputMethod.EXECUTE_ACTION, KodiStringParam("action", action.toString()))
        return makeCall(post)
    }

    /**
     * Goes to home window in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun home(id: KodiID): KodiStringRespond {
        logger.info("Go to home. ID: $id")
        val post = KodiPostData(id, InputMethod.HOME)
        return makeCall(post)
    }

    /**
     * Shows the information dialog
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun info(id: KodiID): KodiStringRespond {
        logger.info("Show Info. ID: $id")
        val post = KodiPostData(id, InputMethod.INFO)
        return makeCall(post)
    }

    /**
     * Navigate left in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun left(id: KodiID): KodiStringRespond {
        logger.info("Left pressed. ID: $id")
        val post = KodiPostData(id, InputMethod.LEFT)
        return makeCall(post)
    }

    /**
     * Navigate left in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun right(id: KodiID): KodiStringRespond {
        logger.info("Right pressed. ID: $id")
        val post = KodiPostData(id, InputMethod.RIGHT)
        return makeCall(post)
    }

    /**
     * Select current item in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun select(id: KodiID): KodiStringRespond {
        logger.info("Select pressed. ID: $id")
        val post = KodiPostData(id, InputMethod.SELECT)
        return makeCall(post)
    }

    /**
     * Select current item in GUI
     *
     * @param id the ID which should be used for the call
     * @param text the text to show
     * @param isDone true if the window should be shown afterwards, if false it does not close
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun sendText(id: KodiID, text: String, isDone: Boolean = true): KodiStringRespond {
        logger.info("Send Text $text // $isDone. ID: $id")
        val post = KodiPostData(id, InputMethod.SEND_TEXT, KodiPropertiesParam(listOf(KodiStringProperty("text", text), KodiBoolProperty("done", isDone))))
        return makeCall(post)
    }

    /**
     * Show the on-screen display for the current player
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun showOSD(id: KodiID): KodiStringRespond {
        logger.info("Show OSD. ID: $id")
        val post = KodiPostData(id, InputMethod.SHOW_OSD)
        return makeCall(post)
    }

    /**
     * Show player process information of the playing item, like video decoder, pixel format, pvr signal strength, ...
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun showPlayerProcessInfo(id: KodiID): KodiStringRespond {
        logger.info("Show OSD. ID: $id")
        val post = KodiPostData(id, InputMethod.SHOW_PLAYER_PROCESS_INFO)
        return makeCall(post)
    }

    /**
     * Navigate up in GUI
     *
     * @param id the ID which should be used for the call
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    fun up(id: KodiID): KodiStringRespond {
        logger.info("Show OSD. ID: $id")
        val post = KodiPostData(id, InputMethod.UP)
        return makeCall(post)
    }
}