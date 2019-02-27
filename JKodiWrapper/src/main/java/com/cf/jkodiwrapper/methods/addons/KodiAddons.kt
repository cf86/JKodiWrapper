package com.cf.jkodiwrapper.methods.addons

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.addons.params.entity.AbstractAddonParam
import com.cf.jkodiwrapper.methods.addons.params.entity.AddonItemsParam
import com.cf.jkodiwrapper.methods.addons.params.entity.AddonMapParam
import com.cf.jkodiwrapper.methods.addons.params.entity.AddonURLParam
import com.cf.jkodiwrapper.methods.addons.params.post.ExecAddonParam
import com.cf.jkodiwrapper.methods.addons.params.post.GetAttributeParam
import com.cf.jkodiwrapper.methods.addons.respond.AddonDetailsRespond
import com.cf.jkodiwrapper.methods.addons.respond.AddonsRespond
import com.cf.jkodiwrapper.types.addon.AddonContent
import com.cf.jkodiwrapper.types.addon.AddonFields
import com.cf.jkodiwrapper.types.addon.AddonID
import com.cf.jkodiwrapper.types.addon.AddonTypes
import com.cf.jkodiwrapper.types.global.GlobalToggle
import com.cf.jkodiwrapper.types.list.ListLimits
import org.slf4j.LoggerFactory

class KodiAddons @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Gets all available addons
     *
     * @param id the ID which should be used for the call
     * @param type Type of Addons which should be returned
     * @param content Content provided by the addon. Only considered for plugins and scripts.
     * @param enabled null for all plugins, true for only enabled, false for only disabled ones
     * @param properties the properties which should be returned by the API. Use AddonFields.getAllAddonFields() to get all
     * @param limits defines how many addons should be returned. Limit will be applied after the call, so a lower number will not increase the performance
     * @param installed null for all plugins, true for only installed, false for only not installed ones
     *
     * @return returns a list of addons depending on the given criteria
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getAddons(id: KodiID, type: AddonTypes = AddonTypes.UNKNOWN, content: AddonContent = AddonContent.UNKNOWN, enabled: Boolean? = null,
                  properties: List<AddonFields> = listOf(), limits: ListLimits = ListLimits(), installed: Boolean? = true): AddonsRespond {
        logger.info("Get Addons. ID: $id")
        val post = KodiPostData(id, AddonsMethod.GET_ADDONS, GetAttributeParam(type, content, enabled, properties, limits, installed))
        return makeCall(post)
    }

    /**
     * Gets details for a specific addon
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be returned
     * @param properties the properties which should be returned by the API. Use AddonFields.getAllAddonFields() to get all
     *
     * @return specific information of the addon
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getAddonDetails(id: KodiID, addonID: AddonID, properties: List<AddonFields> = listOf()): AddonDetailsRespond {
        logger.info("Get AddonDetails for '$addonID'. ID: $id")
//        val post = KodiPostData(id, AddonsMethod.GET_ADDON_DETAILS, GetAddonDetailsParam(addonID, properties))
        val post = KodiPostData(id, AddonsMethod.GET_ADDON_DETAILS, KodiPropertiesParam(listOf(KodiStringProperty("addonid", addonID.toString()),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Enables/Disables a specific addon
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be dis/enabled
     * @param isEnabled true for enabled, false to disable the addon, null to toggle
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setAddonEnabled(id: KodiID, addonID: AddonID, isEnabled: Boolean? = null): KodiStringRespond {
        logger.info("Set Addon enabled: $isEnabled for '$addonID'. ID: $id")
        val post = KodiPostData(id, AddonsMethod.SET_ADDON_ENABLED, KodiPropertiesParam(listOf(KodiStringProperty("addonid", addonID.toString()),
                if (isEnabled == null) KodiStringProperty("enabled", GlobalToggle.TOGGLE.toString()) else KodiBoolProperty("enabled", isEnabled))))
        return makeCall(post)
    }

    /**
     * Executes the given addon with the given parameters (if possible)
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be dis/enabled
     * @param urlPath Path to the addon. Must be start with / or ?
     * @param wait true for wait, else false
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun executeAddon(id: KodiID, addonID: AddonID, urlPath: KodiPath, wait: Boolean = false): KodiStringRespond {
        if (!urlPath.path.startsWith("?") && !urlPath.path.startsWith("/"))
            throw KodiException("Path must start with ? or /")
        return executeAddon(id, addonID, AddonURLParam(urlPath.path), wait)
    }

    /**
     * Executes the given addon with the given parameters (if possible)
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be dis/enabled
     * @param items additional parameter items as Array
     * @param wait true for wait, else false
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun executeAddon(id: KodiID, addonID: AddonID, items: List<String>, wait: Boolean = false): KodiStringRespond {
        return executeAddon(id, addonID, AddonItemsParam(items), wait)
    }

    /**
     * Executes the given addon with the given parameters (if possible)
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be dis/enabled
     * @param params additional Param Map
     * @param wait true for wait, else false
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun executeAddon(id: KodiID, addonID: AddonID, params: Map<String, String>, wait: Boolean = false): KodiStringRespond {
        return executeAddon(id, addonID, AddonMapParam(params), wait)
    }

    /**
     * Executes the given addon with the given parameters (if possible)
     *
     * @param id the ID which should be used for the call
     * @param addonID the ID of the addon which should be dis/enabled
     * @param additionalParams additional params. Can be either a URL, String Array or Param Map. Only one is allowed in this order. The other 2 must be null.
     * @param wait true for wait, else false
     *
     * @return OK if everything went fine, else error
     */
    @Throws(KodiException::class)
    private fun executeAddon(id: KodiID, addonID: AddonID, additionalParams: AbstractAddonParam, wait: Boolean = false): KodiStringRespond {
        logger.info("Execute Addon '$addonID'. ID: $id")
        val post = KodiPostData(id, AddonsMethod.EXECUTE_ADDON, ExecAddonParam(addonID, additionalParams, wait))
        return makeCall(post)
    }
}