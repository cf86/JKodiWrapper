package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.playlist.KodiPlaylist
import com.cf.jkodiwrapper.methods.playlist.params.entity.playlistitem.AbstractPlaylistItem
import com.cf.jkodiwrapper.methods.playlist.respond.GetItemsRespond
import com.cf.jkodiwrapper.methods.playlist.respond.GetPlaylistsRespond
import com.cf.jkodiwrapper.methods.playlist.respond.PropertiesRespond
import com.cf.jkodiwrapper.methods.playlist.respond.entity.PlaylistItems
import com.cf.jkodiwrapper.types.list.ListFieldsAll
import com.cf.jkodiwrapper.types.list.ListItemAll
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.media.MediaArtwork
import com.cf.jkodiwrapper.types.playlist.*
import com.cf.jkodiwrapper.util.RequestRespond
import com.cf.jkodiwrapper.util.RequestUtil
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(RequestUtil::class)
@PowerMockIgnore("javax.net.ssl.*")
class PlaylistTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiPlaylist("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Playlist.GetPlaylists\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.getPlaylists(KodiID(5))
        val expected = GetPlaylistsRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetPlaylists() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":[{\"playlistid\":0,\"type\":\"audio\"},{\"playlistid\":1,\"type\":\"video\"},{\"playlistid\":2,\"type\":\"picture\"}]}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.GetPlaylists\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getPlaylists(KodiID(1))
        val expected = GetPlaylistsRespond(listOf(Playlist(0, "audio"), Playlist(1, "video"), Playlist(2, "picture")))
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testAddItem() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.Add\", \"params\": {\"playlistid\":0,\"item\":{\"file\":\"/path/to/file.mp3\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.addItem(KodiID(1), PlaylistID(0), listOf(AbstractPlaylistItem.getFileItem("/path/to/file.mp3")))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testClear() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.Clear\", \"params\": {\"playlistid\":0}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.clear(KodiID(1), PlaylistID(0))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetItems() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"items\":[{\"album\":\"A1\",\"albumartist\":[\"A1\"],\"albumid\":-1,\"art\":{\"thumb\":\"image://music@/path/to/file1.mp3/\"},\"artist\":[\"A1\"],\"comment\":\"\",\"compilation\":false,\"contributors\":[],\"dateadded\":\"\",\"disc\":1,\"displayartist\":\"A1\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":205,\"fanart\":\"\",\"file\":\"/path/to/file1.mp3\",\"genre\":[\"Rock\"],\"label\":\"A1\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"thumbnail\":\"image://music@/path/to/file1.mp3/\",\"title\":\"17\",\"track\":3,\"type\":\"song\",\"userrating\":0,\"votes\":0,\"year\":2013}],\"limits\":{\"end\":1,\"start\":0,\"total\":1}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.GetItems\", \"params\": {\"playlistid\":0,\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumlabel\",\"albumreleasetype\",\"art\",\"artist\",\"artistid\",\"cast\",\"channel\",\"channelnumber\",\"channeltype\",\"comment\",\"compilation\",\"contributors\",\"country\",\"dateadded\",\"description\",\"director\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"endtime\",\"episode\",\"episodeguide\",\"fanart\",\"file\",\"firstaired\",\"genre\",\"genreid\",\"hidden\",\"imdbnumber\",\"lastplayed\",\"locked\",\"lyrics\",\"mood\",\"mpaa\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"originaltitle\",\"playcount\",\"plot\",\"plotoutline\",\"premiered\",\"productioncode\",\"rating\",\"releasetype\",\"resume\",\"runtime\",\"season\",\"set\",\"setid\",\"showlink\",\"showtitle\",\"sorttitle\",\"specialsortepisode\",\"specialsortseason\",\"starttime\",\"streamdetails\",\"studio\",\"style\",\"tag\",\"tagline\",\"theme\",\"thumbnail\",\"title\",\"top250\",\"track\",\"trailer\",\"tvshowid\",\"uniqueid\",\"userrating\",\"votes\",\"watchedepisodes\",\"writer\",\"year\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getItems(KodiID(1), PlaylistID(0), ListFieldsAll.getAllFields())
        val item = ListItemAll(null, 0, "tv", null, false, false, null)
        item.album = "A1"; item.albumartist = listOf("A1"); item.albumid = -1; item.art = MediaArtwork(thumb = "image://music@/path/to/file1.mp3/")
        item.artist = listOf("A1"); item.comment = ""; item.compilation = false; item.dateadded = ""; item.disc = 1; item.displayartist = "A1"; item.displaycomposer = ""
        item.displayconductor = ""; item.displaylyricist = ""; item.displayorchestra = ""; item.duration = 205; item.fanart = ""; item.file = "/path/to/file1.mp3"
        item.genre = listOf("Rock"); item.label = "A1"; item.lastplayed = ""; item.lyrics = ""; item.musicbrainzalbumid = ""; item.musicbrainztrackid = ""
        item.playcount = 0; item.rating = 0.0; item.thumbnail = "image://music@/path/to/file1.mp3/"; item.title = "17"; item.track = 3; item.type = "song"
        item.userrating = 0; item.votes = "0"; item.year = 2013
        val expected = GetItemsRespond(PlaylistItems(listOf(item), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"size\":2,\"type\":\"audio\"}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.GetProperties\", \"params\": {\"playlistid\":0,\"properties\":[\"type\",\"size\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProperties(KodiID(1), PlaylistID(0), PlaylistPropertyName.getAllFields())
        val expected = PropertiesRespond(PlaylistPropertyValue(2, "audio"))
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testInsert() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.Insert\", \"params\": {\"playlistid\":0,\"position\":1,\"item\":{\"file\":\"/path/to/file.mp3\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.insert(KodiID(1), PlaylistID(0), PlaylistPosition(1), listOf(AbstractPlaylistItem.getFileItem("/path/to/file.mp3")))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRemove() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.Remove\", \"params\": {\"playlistid\":0,\"position\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.remove(KodiID(1), PlaylistID(0), PlaylistPosition(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testSwap() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Playlist.Swap\", \"params\": {\"playlistid\":0,\"position1\":1,\"position2\":2}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.swap(KodiID(1), PlaylistID(0), PlaylistPosition(1), PlaylistPosition(2))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        Assert.assertEquals(expected, actual)
    }
}