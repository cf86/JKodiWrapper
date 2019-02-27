package com.cf.jkodiwrapper.methods.profiles

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiStringArrParam
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.profiles.params.GetProfilesParam
import com.cf.jkodiwrapper.methods.profiles.params.LoadProfileParam
import com.cf.jkodiwrapper.methods.profiles.respond.ProfileRespond
import com.cf.jkodiwrapper.methods.profiles.respond.ProfilesRespond
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.profiles.ProfilePassword
import com.cf.jkodiwrapper.types.profiles.ProfilesFieldsProfile
import org.slf4j.LoggerFactory

class KodiProfiles @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Retrieve the current profile
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be returned. Use ProfilesFieldsProfile.getAllFields() to get all
     *
     * @return the current Profile
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getCurrentProfile(id: KodiID, properties: List<ProfilesFieldsProfile> = listOf()): ProfileRespond {
        logger.info("Get Current Profile. ID: $id")
        val post = KodiPostData(id, ProfilesMethod.GET_CURRENT_PROFILE, KodiStringArrParam("properties", properties.map { it.field }))
        return makeCall(post)
    }

    /**
     * Retrieve all profiles
     *
     * @param id the ID which should be used for the call
     * @param properties the properties which should be returned. Use ProfilesFieldsProfile.getAllFields() to get all
     * @param limits limits the number of received profiles. This will applied afterwards, so a lower limit will not increase performance
     * @param sort define how the profiles should be sorted
     *
     * @return the profiles
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getProfiles(id: KodiID, properties: List<ProfilesFieldsProfile> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): ProfilesRespond {
        logger.info("Get all Profiles. ID: $id")
        val post = KodiPostData(id, ProfilesMethod.GET_PROFILES, GetProfilesParam(properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieve all profiles
     *
     * @param id the ID which should be used for the call
     * @param profile the profile name to load
     * @param prompt open a Password Prompt in Kodi
     * @param password the password if no prompt is used
     *
     * @return the profiles
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun loadProfile(id: KodiID, profile: String, prompt: Boolean = false, password: ProfilePassword = ProfilePassword()): KodiStringRespond {
        logger.info("Load Profile: $profile. ID: $id")
        val post = KodiPostData(id, ProfilesMethod.LOAD_PROFILE, LoadProfileParam(profile, prompt, password))
        return makeCall(post)
    }
}