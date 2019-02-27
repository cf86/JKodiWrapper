package com.cf.jkodiwrapper.methods.favourites

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiNullProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiStringProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.favourites.params.AddFavParam
import com.cf.jkodiwrapper.methods.favourites.respond.FavouritesRespond
import com.cf.jkodiwrapper.types.favourite.FavouriteFieldsFavourite
import com.cf.jkodiwrapper.types.favourite.FavouriteType
import org.slf4j.LoggerFactory

class KodiFavourites @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Retrieve all favourites
     *
     * @param id the ID which should be used for the call
     * @param type Type of the Favourites which should be returned, or null for all
     * @param properties the properties which should be returned. Use FavouriteFieldsFavourite.getAllFields() to get all fields
     *
     * @return returns a list with all favourites
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getFavourites(id: KodiID, type: FavouriteType? = null, properties: List<FavouriteFieldsFavourite> = listOf()): FavouritesRespond {
        logger.info("Get Addons. ID: $id")
        val post = KodiPostData(id, FavouritesMethod.GET_FAVOURITES, KodiPropertiesParam(listOf(if (type == null) KodiNullProperty("type") else KodiStringProperty("type", type.toString()),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Add a favourite with the given details
     *
     * @param id the ID which should be used for the call
     * @param title the title of the favorite
     * @param type Type of the favorite
     * @param path the path to the file e.g. /path/to/file: REQUIRED for media and script favourites types
     * @param window the window REQUIRED for window favourite type
     * @param windowparameter optional window parameter to use
     * @param thumbnail the thumbnail to use, an absolute path to the file e.g. /path/to/file
     *
     * @return OK if everything went fine, else false
     */
    @Throws(KodiException::class)
    fun addFavourite(id: KodiID, title: String, type: FavouriteType, path: String?, window: String?, windowparameter: String?, thumbnail: String?): KodiStringRespond {
        logger.info("Get Addons. ID: $id")
        val post = KodiPostData(id, FavouritesMethod.ADD_FAVOURITE, AddFavParam(title, type, path, window, windowparameter, thumbnail))
        return makeCall(post)
    }
}