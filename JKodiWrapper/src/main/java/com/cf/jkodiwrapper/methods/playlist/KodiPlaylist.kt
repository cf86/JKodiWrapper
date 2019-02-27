package com.cf.jkodiwrapper.methods.playlist

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiPropertiesParam
import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty
import com.cf.jkodiwrapper.general.request.param.post.property.KodiListProperty
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem.AbstractPlaylistItem
import com.cf.jkodiwrapper.methods.playlist.params.post.AddPlaylistItemParam
import com.cf.jkodiwrapper.methods.playlist.params.post.GetItemsParam
import com.cf.jkodiwrapper.methods.playlist.params.post.InsertPlaylistItemParam
import com.cf.jkodiwrapper.methods.playlist.respond.GetItemsRespond
import com.cf.jkodiwrapper.methods.playlist.respond.GetPlaylistsRespond
import com.cf.jkodiwrapper.methods.playlist.respond.PropertiesRespond
import com.cf.jkodiwrapper.types.list.ListFieldsAll
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.playlist.PlaylistID
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition
import com.cf.jkodiwrapper.types.playlist.PlaylistPropertyName
import org.slf4j.LoggerFactory

class KodiPlaylist @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Returns all existing playlists
     *
     * @param id the ID which should be used for the call
     *
     * @return a list with all playlists
     */
    fun getPlaylists(id: KodiID): GetPlaylistsRespond {
        logger.info("Get Playlists. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.GET_PLAYLISTS)
        return makeCall(post)
    }

    /**
     * Add item(s) to playlist
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID to which the items should be added
     * @param items the items to add. Get AbstractPlaylistItem.get...() to get specific instances of items
     *
     * @return OK if everything fine, else error
     */
    @Throws(KodiException::class)
    fun addItem(id: KodiID, playlistID: PlaylistID, items: List<AbstractPlaylistItem>): KodiStringRespond {
        logger.info("Add items $items to $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.ADD, AddPlaylistItemParam(playlistID, items))
        return makeCall(post)
    }

    /**
     * Clear playlist
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID to clear
     *
     * @return OK if everything fine, else error
     */
    fun clear(id: KodiID, playlistID: PlaylistID): KodiStringRespond {
        logger.info("Clear Playlist $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.CLEAR, KodiPropertiesParam(listOf(KodiIntProperty("playlistid", playlistID.id))))
        return makeCall(post)
    }

    /**
     * Get all items from playlist
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID from which to get the items
     * @param properties all properties to get. Use ListFieldsAll.getAllFields() to get all fields
     * @param limits limits the number of items. Limits are applied after the call, so lower limits to not increase performance
     * @param sort sorting method of the items
     *
     * @return a list with all items of the given playlist
     */
    @JvmOverloads
    fun getItems(id: KodiID, playlistID: PlaylistID, properties: List<ListFieldsAll> = listOf(), limits: ListLimits = ListLimits(), sort: ListSort = ListSort()): GetItemsRespond {
        logger.info("Get Items for $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.GET_ITEMS, GetItemsParam(playlistID, properties, limits, sort))
        return makeCall(post)
    }

    /**
     * Retrieves the values of the given properties
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID from which to get the items
     * @param properties all properties to get. Use PlaylistPropertyName.getAllFields() to get all fields
     *
     * @return a list with all properties of the given playlist
     */
    @JvmOverloads
    fun getProperties(id: KodiID, playlistID: PlaylistID, properties: List<PlaylistPropertyName> = listOf()): PropertiesRespond {
        logger.info("Get Properties for $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.GET_PROPERTIES, KodiPropertiesParam(listOf(KodiIntProperty("playlistid", playlistID.id),
                KodiListProperty("properties", properties))))
        return makeCall(post)
    }

    /**
     * Insert item(s) into playlist. Does not work for picture playlists (aka slideshows).
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID from which to get the items
     * @param position the position where to insert the items
     * @param items the items to insert into the playlist
     *
     * @return OK if everything fine, else error
     */
    fun insert(id: KodiID, playlistID: PlaylistID, position: PlaylistPosition, items: List<AbstractPlaylistItem>): KodiStringRespond {
        logger.info("Insert $items at $position for Playlist $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.INSERT, InsertPlaylistItemParam(playlistID, position, items))
        return makeCall(post)
    }

    /**
     * Remove item from playlist. Does not work for picture playlists (aka slideshows).
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID from which to get the items
     * @param position the position which should be removed
     *
     * @return OK if everything fine, else error
     */
    fun remove(id: KodiID, playlistID: PlaylistID, position: PlaylistPosition): KodiStringRespond {
        logger.info("Remove $position from Playlist $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.REMOVE, KodiPropertiesParam(listOf(KodiIntProperty("playlistid", playlistID.id),
                KodiIntProperty("position", position.pos))))
        return makeCall(post)
    }

    /**
     * Swap items in the playlist. Does not work for picture playlists (aka slideshows).
     *
     * @param id the ID which should be used for the call
     * @param playlistID the playlist ID from which to get the items
     * @param position1 position1 which should be swapped with 2
     * @param position2 position2 which should be swapped with 1
     *
     * @return OK if everything fine, else error
     */
    fun swap(id: KodiID, playlistID: PlaylistID, position1: PlaylistPosition, position2: PlaylistPosition): KodiStringRespond {
        logger.info("Swap $position1 with $position2 for Playlist $playlistID. ID: $id")
        val post = KodiPostData(id, PlaylistMethod.SWAP, KodiPropertiesParam(listOf(KodiIntProperty("playlistid", playlistID.id),
                KodiIntProperty("position1", position1.pos), KodiIntProperty("position2", position2.pos))))
        return makeCall(post)
    }
}