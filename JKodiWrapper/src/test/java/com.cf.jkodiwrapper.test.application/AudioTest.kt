package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.audio.KodiAudio
import com.cf.jkodiwrapper.methods.audio.respond.*
import com.cf.jkodiwrapper.methods.audio.respond.entity.*
import com.cf.jkodiwrapper.types.audio.*
import com.cf.jkodiwrapper.types.library.LibraryDetailsGenre
import com.cf.jkodiwrapper.types.library.LibraryFieldsGenre
import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.list.*
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
class AudioTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiAudio("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"AudioLibrary.Clean\", \"params\": {\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.clean(KodiID(5), true)
        val expected = KodiStringRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testClean() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.Clean\", \"params\": {\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.clean(KodiID(1), true)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetAlbums() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"albums\":[{\"albumid\":1,\"albumlabel\":\"\",\"artist\":[\"A1\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A1\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image.mp3/\",\"title\":\"A1\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2013},{\"albumid\":2,\"albumlabel\":\"\",\"artist\":[\"A2\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A2\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A2\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image2.mp3/\",\"title\":\"A2\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2011}],\"limits\":{\"end\":2,\"start\":0,\"total\":2}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetAlbums\", \"params\": {\"properties\":[\"albumlabel\",\"artist\",\"artistid\",\"compilation\",\"dateadded\",\"description\",\"displayartist\",\"fanart\",\"genre\",\"genreid\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"playcount\",\"rating\",\"releasetype\",\"style\",\"theme\",\"thumbnail\",\"title\",\"type\",\"userrating\",\"votes\",\"year\"],\"includesingles\":false,\"allroles\":false,\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getAlbums(KodiID(1), AudioFieldsAlbum.getAllFields())
        val a1 = AudioDetailsAlbum(1, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a1.artist = listOf("A1");a1.artistid = listOf(2);a1.dateadded = "2018-06-03 22:26:16";a1.displayartist = "A1";a1.fanart = "";a1.genre = listOf("Rock")
        a1.genreid = listOf(1);a1.label = "A1";a1.musicbrainzalbumartistid = listOf("");a1.musicbrainzalbumid = "";a1.rating = 0.0;a1.thumbnail = "image://music@image.mp3/"
        a1.title = "A1";a1.userrating = 0;a1.votes = 0;a1.year = 2013
        val a2 = AudioDetailsAlbum(2, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a2.artist = listOf("A2");a2.artistid = listOf(2);a2.dateadded = "2018-06-03 22:26:16";a2.displayartist = "A2";a2.fanart = "";a2.genre = listOf("Rock")
        a2.genreid = listOf(1);a2.label = "A2";a2.musicbrainzalbumartistid = listOf("");a2.musicbrainzalbumid = "";a2.rating = 0.0;a2.thumbnail = "image://music@image2.mp3/"
        a2.title = "A2";a2.userrating = 0;a2.votes = 0;a2.year = 2011
        val expected = AlbumsRespond(Albums(listOf(a1, a2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetAlbumsDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"albumdetails\":{\"albumid\":1,\"albumlabel\":\"\",\"artist\":[\"A1\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A1\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image.mp3/\",\"title\":\"A1\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2013}}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetAlbumDetails\", \"params\": {\"albumid\":1,\"properties\":[\"albumlabel\",\"artist\",\"artistid\",\"compilation\",\"dateadded\",\"description\",\"displayartist\",\"fanart\",\"genre\",\"genreid\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"playcount\",\"rating\",\"releasetype\",\"style\",\"theme\",\"thumbnail\",\"title\",\"type\",\"userrating\",\"votes\",\"year\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getAlbumDetails(KodiID(1), LibraryID(1), AudioFieldsAlbum.getAllFields())
        val a1 = AudioDetailsAlbum(1, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a1.artist = listOf("A1");a1.artistid = listOf(2);a1.dateadded = "2018-06-03 22:26:16";a1.displayartist = "A1";a1.fanart = "";a1.genre = listOf("Rock")
        a1.genreid = listOf(1);a1.label = "A1";a1.musicbrainzalbumartistid = listOf("");a1.musicbrainzalbumid = "";a1.rating = 0.0;a1.thumbnail = "image://music@image.mp3/"
        a1.title = "A1";a1.userrating = 0;a1.votes = 0;a1.year = 2013
        val expected = AlbumRespond(AlbumDetails(a1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetArtists() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"artists\":[{\"artist\":\"A1\",\"artistid\":2,\"born\":\"\",\"compilationartist\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"died\":\"\",\"disbanded\":\"\",\"fanart\":\"\",\"formed\":\"\",\"genre\":[],\"instrument\":[],\"isalbumartist\":true,\"label\":\"A1\",\"mood\":[],\"musicbrainzartistid\":[\"\"],\"roles\":[{\"role\":\"Artist\",\"roleid\":1}],\"songgenres\":[{\"genreid\":1,\"title\":\"Rock\"}],\"style\":[],\"thumbnail\":\"\",\"yearsactive\":[]}],\"limits\":{\"end\":1,\"start\":0,\"total\":1}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetArtists\", \"params\": {\"properties\":[\"born\",\"compilationartist\",\"dateadded\",\"description\",\"died\",\"disbanded\",\"fanart\",\"formed\",\"genre\",\"instrument\",\"isalbumartist\",\"mood\",\"musicbrainzartistid\",\"roles\",\"songgenres\",\"style\",\"thumbnail\",\"yearsactive\"],\"albumartistsonly\":false,\"allroles\":false,\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getArtists(KodiID(1), AudioFieldsArtist.getAllFields())
        val a1 = AudioDetailsArtist("A1", 2, "", false, "", "", "", "", ArrayList(), true,
                ArrayList(), listOf(""), listOf(AudioArtistRoles("Artist", 1)), listOf(AudioDetailsGenres(1, "Rock")), ArrayList(), ArrayList())
        a1.dateadded = "2018-06-03 22:26:16";a1.fanart = "";a1.genre = ArrayList();a1.label = "A1";a1.thumbnail = ""

        val expected = ArtistsRespond(Artists(listOf(a1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetArtistDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"artistdetails\":{\"artist\":\"A1\",\"artistid\":2,\"born\":\"\",\"compilationartist\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"died\":\"\",\"disbanded\":\"\",\"fanart\":\"\",\"formed\":\"\",\"genre\":[],\"instrument\":[],\"isalbumartist\":true,\"label\":\"A1\",\"mood\":[],\"musicbrainzartistid\":[\"\"],\"roles\":[{\"role\":\"Artist\",\"roleid\":1}],\"songgenres\":[{\"genreid\":1,\"title\":\"Rock\"}],\"style\":[],\"thumbnail\":\"\",\"yearsactive\":[]}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetArtistDetails\", \"params\": {\"artistid\":2,\"properties\":[\"born\",\"compilationartist\",\"dateadded\",\"description\",\"died\",\"disbanded\",\"fanart\",\"formed\",\"genre\",\"instrument\",\"isalbumartist\",\"mood\",\"musicbrainzartistid\",\"roles\",\"songgenres\",\"style\",\"thumbnail\",\"yearsactive\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getArtistDetails(KodiID(1), LibraryID(2), AudioFieldsArtist.getAllFields())
        val a1 = AudioDetailsArtist("A1", 2, "", false, "", "", "", "", ArrayList(), true,
                ArrayList(), listOf(""), listOf(AudioArtistRoles("Artist", 1)), listOf(AudioDetailsGenres(1, "Rock")), ArrayList(), ArrayList())
        a1.dateadded = "2018-06-03 22:26:16";a1.fanart = "";a1.genre = ArrayList();a1.label = "A1";a1.thumbnail = ""

        val expected = ArtistRespond(ArtistDetails(a1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetGenres() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"genres\":[{\"genreid\":1,\"label\":\"Rock\",\"thumbnail\":\"\",\"title\":\"Rock\"}],\"limits\":{\"end\":1,\"start\":0,\"total\":1}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.getGenres\", \"params\": {\"properties\":[\"title\",\"thumbnail\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getGenres(KodiID(1), LibraryFieldsGenre.getAllFields())
        val g1 = LibraryDetailsGenre(1, "", "Rock")
        g1.label = "Rock"

        val expected = GenresRespond(Genres(listOf(g1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"missingartistid\":1}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetProperties\", \"params\": {\"properties\":[\"missingartistid\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProperties(KodiID(1), AudioPropertyName.getAllFields())
        val expected = PropertiesRespond(AudioPropertyValue(1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetRecentlyAddedAlbums() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"albums\":[{\"albumid\":1,\"albumlabel\":\"\",\"artist\":[\"A1\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A1\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image.mp3/\",\"title\":\"A1\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2013},{\"albumid\":2,\"albumlabel\":\"\",\"artist\":[\"A2\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A2\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A2\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image2.mp3/\",\"title\":\"A2\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2011}],\"limits\":{\"end\":2,\"start\":0,\"total\":2}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetRecentlyAddedAlbums\", \"params\": {\"properties\":[\"albumlabel\",\"artist\",\"artistid\",\"compilation\",\"dateadded\",\"description\",\"displayartist\",\"fanart\",\"genre\",\"genreid\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"playcount\",\"rating\",\"releasetype\",\"style\",\"theme\",\"thumbnail\",\"title\",\"type\",\"userrating\",\"votes\",\"year\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getRecentlyAddedAlbums(KodiID(1), AudioFieldsAlbum.getAllFields())
        val a1 = AudioDetailsAlbum(1, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a1.artist = listOf("A1");a1.artistid = listOf(2);a1.dateadded = "2018-06-03 22:26:16";a1.displayartist = "A1";a1.fanart = "";a1.genre = listOf("Rock")
        a1.genreid = listOf(1);a1.label = "A1";a1.musicbrainzalbumartistid = listOf("");a1.musicbrainzalbumid = "";a1.rating = 0.0;a1.thumbnail = "image://music@image.mp3/"
        a1.title = "A1";a1.userrating = 0;a1.votes = 0;a1.year = 2013
        val a2 = AudioDetailsAlbum(2, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a2.artist = listOf("A2");a2.artistid = listOf(2);a2.dateadded = "2018-06-03 22:26:16";a2.displayartist = "A2";a2.fanart = "";a2.genre = listOf("Rock")
        a2.genreid = listOf(1);a2.label = "A2";a2.musicbrainzalbumartistid = listOf("");a2.musicbrainzalbumid = "";a2.rating = 0.0;a2.thumbnail = "image://music@image2.mp3/"
        a2.title = "A2";a2.userrating = 0;a2.votes = 0;a2.year = 2011
        val expected = AlbumsRespond(Albums(listOf(a1, a2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetRecentlyAddedSongs() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":2,\"start\":0,\"total\":2},\"songs\":[{\"album\":\"A1\",\"albumartist\":[\"A1\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A1\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A1\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":194,\"fanart\":\"\",\"file\":\"/path/to/file1.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":20,\"thumbnail\":\"image://music@/path/to/file1.mp3/\",\"title\":\"A1\",\"track\":2,\"userrating\":0,\"votes\":0,\"year\":2005},{\"album\":\"A2\",\"albumartist\":[\"A2\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A2\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A2\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":179,\"fanart\":\"\",\"file\":\"/path/to/file2.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A2\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":21,\"thumbnail\":\"image://music@/path/to/file2.mp3/\",\"title\":\"A2\",\"track\":4,\"userrating\":0,\"votes\":0,\"year\":2005}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetRecentlyAddedSongs\", \"params\": {\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumreleasetype\",\"artist\",\"artistid\",\"comment\",\"contributors\",\"dateadded\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"fanart\",\"file\",\"genre\",\"genreid\",\"lastplayed\",\"lyrics\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"playcount\",\"rating\",\"thumbnail\",\"title\",\"track\",\"userrating\",\"votes\",\"year\"],\"albumlimit\":2000000,\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getRecentlyAddedSongs(KodiID(1), AudioFieldsSong.getAllFields())
        val s1 = AudioDetailsSong("A1", listOf("A1"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 194, "/path/to/file1.mp3", "", "", ArrayList(), ArrayList(), "", 0, 20, 2)
        s1.artist = listOf("A1"); s1.artistid = listOf(2); s1.dateadded = "2018-06-03 22:26:15"; s1.displayartist = "A1"; s1.fanart = ""; s1.file = "/path/to/file1.mp3"
        s1.genre = listOf("Rock"); s1.genreid = listOf(1); s1.label = "A1"; s1.musicbrainzartistid = listOf(); s1.musicbrainzalbumid = ""; s1.rating = 0.0; s1.title = "A1"
        s1.thumbnail = "image://music@/path/to/file1.mp3/"; s1.userrating = 0; s1.votes = 0; s1.year = 2005
        val s2 = AudioDetailsSong("A2", listOf("A2"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 179, "/path/to/file2.mp3", "", "", ArrayList(), ArrayList(), "", 0, 21, 4)
        s2.artist = listOf("A2"); s2.artistid = listOf(2); s2.dateadded = "2018-06-03 22:26:15"; s2.displayartist = "A2"; s2.fanart = ""; s2.file = "/path/to/file2.mp3"
        s2.genre = listOf("Rock"); s2.genreid = listOf(1); s2.label = "A2"; s2.musicbrainzartistid = listOf(); s2.musicbrainzalbumid = ""; s2.rating = 0.0; s2.title = "A2"
        s2.thumbnail = "image://music@/path/to/file2.mp3/"; s2.userrating = 0; s2.votes = 0; s2.year = 2005
        val expected = SongsRespond(Songs(listOf(s1, s2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetRecentlyPlayedAlbums() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"albums\":[{\"albumid\":1,\"albumlabel\":\"\",\"artist\":[\"A1\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A1\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image.mp3/\",\"title\":\"A1\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2013},{\"albumid\":2,\"albumlabel\":\"\",\"artist\":[\"A2\"],\"artistid\":[2],\"compilation\":false,\"dateadded\":\"2018-06-03 22:26:16\",\"description\":\"\",\"displayartist\":\"A2\",\"fanart\":\"\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A2\",\"mood\":[],\"musicbrainzalbumartistid\":[\"\"],\"musicbrainzalbumid\":\"\",\"playcount\":0,\"rating\":0,\"releasetype\":\"album\",\"style\":[],\"theme\":[],\"thumbnail\":\"image://music@image2.mp3/\",\"title\":\"A2\",\"type\":\"\",\"userrating\":0,\"votes\":0,\"year\":2011}],\"limits\":{\"end\":2,\"start\":0,\"total\":2}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetRecentlyPlayedAlbums\", \"params\": {\"properties\":[\"albumlabel\",\"artist\",\"artistid\",\"compilation\",\"dateadded\",\"description\",\"displayartist\",\"fanart\",\"genre\",\"genreid\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"playcount\",\"rating\",\"releasetype\",\"style\",\"theme\",\"thumbnail\",\"title\",\"type\",\"userrating\",\"votes\",\"year\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getRecentlyPlayedAlbums(KodiID(1), AudioFieldsAlbum.getAllFields())
        val a1 = AudioDetailsAlbum(1, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a1.artist = listOf("A1");a1.artistid = listOf(2);a1.dateadded = "2018-06-03 22:26:16";a1.displayartist = "A1";a1.fanart = "";a1.genre = listOf("Rock")
        a1.genreid = listOf(1);a1.label = "A1";a1.musicbrainzalbumartistid = listOf("");a1.musicbrainzalbumid = "";a1.rating = 0.0;a1.thumbnail = "image://music@image.mp3/"
        a1.title = "A1";a1.userrating = 0;a1.votes = 0;a1.year = 2013
        val a2 = AudioDetailsAlbum(2, "", false, "", ArrayList(), 0, "album", ArrayList(), ArrayList(), "")
        a2.artist = listOf("A2");a2.artistid = listOf(2);a2.dateadded = "2018-06-03 22:26:16";a2.displayartist = "A2";a2.fanart = "";a2.genre = listOf("Rock")
        a2.genreid = listOf(1);a2.label = "A2";a2.musicbrainzalbumartistid = listOf("");a2.musicbrainzalbumid = "";a2.rating = 0.0;a2.thumbnail = "image://music@image2.mp3/"
        a2.title = "A2";a2.userrating = 0;a2.votes = 0;a2.year = 2011
        val expected = AlbumsRespond(Albums(listOf(a1, a2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetRecentlPlayedSongs() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":2,\"start\":0,\"total\":2},\"songs\":[{\"album\":\"A1\",\"albumartist\":[\"A1\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A1\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A1\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":194,\"fanart\":\"\",\"file\":\"/path/to/file1.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":20,\"thumbnail\":\"image://music@/path/to/file1.mp3/\",\"title\":\"A1\",\"track\":2,\"userrating\":0,\"votes\":0,\"year\":2005},{\"album\":\"A2\",\"albumartist\":[\"A2\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A2\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A2\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":179,\"fanart\":\"\",\"file\":\"/path/to/file2.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A2\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":21,\"thumbnail\":\"image://music@/path/to/file2.mp3/\",\"title\":\"A2\",\"track\":4,\"userrating\":0,\"votes\":0,\"year\":2005}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetRecentlyAddedSongs\", \"params\": {\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumreleasetype\",\"artist\",\"artistid\",\"comment\",\"contributors\",\"dateadded\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"fanart\",\"file\",\"genre\",\"genreid\",\"lastplayed\",\"lyrics\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"playcount\",\"rating\",\"thumbnail\",\"title\",\"track\",\"userrating\",\"votes\",\"year\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getRecentlyPlayedSongs(KodiID(1), AudioFieldsSong.getAllFields())
        val s1 = AudioDetailsSong("A1", listOf("A1"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 194, "/path/to/file1.mp3", "", "", ArrayList(), ArrayList(), "", 0, 20, 2)
        s1.artist = listOf("A1"); s1.artistid = listOf(2); s1.dateadded = "2018-06-03 22:26:15"; s1.displayartist = "A1"; s1.fanart = ""; s1.file = "/path/to/file1.mp3"
        s1.genre = listOf("Rock"); s1.genreid = listOf(1); s1.label = "A1"; s1.musicbrainzartistid = listOf(); s1.musicbrainzalbumid = ""; s1.rating = 0.0; s1.title = "A1"
        s1.thumbnail = "image://music@/path/to/file1.mp3/"; s1.userrating = 0; s1.votes = 0; s1.year = 2005
        val s2 = AudioDetailsSong("A2", listOf("A2"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 179, "/path/to/file2.mp3", "", "", ArrayList(), ArrayList(), "", 0, 21, 4)
        s2.artist = listOf("A2"); s2.artistid = listOf(2); s2.dateadded = "2018-06-03 22:26:15"; s2.displayartist = "A2"; s2.fanart = ""; s2.file = "/path/to/file2.mp3"
        s2.genre = listOf("Rock"); s2.genreid = listOf(1); s2.label = "A2"; s2.musicbrainzartistid = listOf(); s2.musicbrainzalbumid = ""; s2.rating = 0.0; s2.title = "A2"
        s2.thumbnail = "image://music@/path/to/file2.mp3/"; s2.userrating = 0; s2.votes = 0; s2.year = 2005
        val expected = SongsRespond(Songs(listOf(s1, s2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetRoles() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":1,\"start\":0,\"total\":1},\"roles\":[{\"label\":\"Artist\",\"roleid\":1,\"title\":\"Artist\"}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetRoles\", \"params\": {\"properties\":[\"title\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getRoles(KodiID(1), AudioFieldsRole.getAllFields())
        val r1 = AudioDetailsRole(1)
        r1.label = "Artist"; r1.title = "Artist"
        val expected = RolesRespond(Roles(listOf(r1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetSongDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"songdetails\":{\"album\":\"A1\",\"albumartist\":[\"A1\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A1\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A1\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":194,\"fanart\":\"\",\"file\":\"/path/to/file1.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":20,\"thumbnail\":\"image://music@/path/to/file1.mp3/\",\"title\":\"A1\",\"track\":2,\"userrating\":0,\"votes\":0,\"year\":2005}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetSongDetails\", \"params\": {\"songid\":20,\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumreleasetype\",\"artist\",\"artistid\",\"comment\",\"contributors\",\"dateadded\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"fanart\",\"file\",\"genre\",\"genreid\",\"lastplayed\",\"lyrics\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"playcount\",\"rating\",\"thumbnail\",\"title\",\"track\",\"userrating\",\"votes\",\"year\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getSongDetails(KodiID(1), LibraryID(20), AudioFieldsSong.getAllFields())
        val s1 = AudioDetailsSong("A1", listOf("A1"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 194, "/path/to/file1.mp3", "", "", ArrayList(), ArrayList(), "", 0, 20, 2)
        s1.artist = listOf("A1"); s1.artistid = listOf(2); s1.dateadded = "2018-06-03 22:26:15"; s1.displayartist = "A1"; s1.fanart = ""; s1.file = "/path/to/file1.mp3"
        s1.genre = listOf("Rock"); s1.genreid = listOf(1); s1.label = "A1"; s1.musicbrainzartistid = listOf(); s1.musicbrainzalbumid = ""; s1.rating = 0.0; s1.title = "A1"
        s1.thumbnail = "image://music@/path/to/file1.mp3/"; s1.userrating = 0; s1.votes = 0; s1.year = 2005
        val expected = SongDetailsRespond(SongDetails(s1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetSongs() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":1,\"start\":0,\"total\":1},\"songs\":[{\"album\":\"A1\",\"albumartist\":[\"A1\"],\"albumartistid\":[2],\"albumid\":5,\"albumreleasetype\":\"album\",\"artist\":[\"A1\"],\"artistid\":[2],\"comment\":\"\",\"contributors\":[],\"dateadded\":\"2018-06-03 22:26:15\",\"disc\":1,\"displayartist\":\"A1\",\"displaycomposer\":\"\",\"displayconductor\":\"\",\"displaylyricist\":\"\",\"displayorchestra\":\"\",\"duration\":194,\"fanart\":\"\",\"file\":\"/path/to/file1.mp3\",\"genre\":[\"Rock\"],\"genreid\":[1],\"label\":\"A1\",\"lastplayed\":\"\",\"lyrics\":\"\",\"mood\":[],\"musicbrainzalbumartistid\":[],\"musicbrainzalbumid\":\"\",\"musicbrainzartistid\":[],\"musicbrainztrackid\":\"\",\"playcount\":0,\"rating\":0,\"songid\":20,\"thumbnail\":\"image://music@/path/to/file1.mp3/\",\"title\":\"A1\",\"track\":2,\"userrating\":0,\"votes\":0,\"year\":2005}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.GetSongs\", \"params\": {\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumreleasetype\",\"artist\",\"artistid\",\"comment\",\"contributors\",\"dateadded\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"fanart\",\"file\",\"genre\",\"genreid\",\"lastplayed\",\"lyrics\",\"mood\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"playcount\",\"rating\",\"thumbnail\",\"title\",\"track\",\"userrating\",\"votes\",\"year\"],\"includesingles\":true,\"allroles\":false,\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getSongs(KodiID(1), AudioFieldsSong.getAllFields())
        val s1 = AudioDetailsSong("A1", listOf("A1"), listOf(2), 5, "album", "", ArrayList(), 1, "", "", "",
                "", 194, "/path/to/file1.mp3", "", "", ArrayList(), ArrayList(), "", 0, 20, 2)
        s1.artist = listOf("A1"); s1.artistid = listOf(2); s1.dateadded = "2018-06-03 22:26:15"; s1.displayartist = "A1"; s1.fanart = ""; s1.file = "/path/to/file1.mp3"
        s1.genre = listOf("Rock"); s1.genreid = listOf(1); s1.label = "A1"; s1.musicbrainzartistid = listOf(); s1.musicbrainzalbumid = ""; s1.rating = 0.0; s1.title = "A1"
        s1.thumbnail = "image://music@/path/to/file1.mp3/"; s1.userrating = 0; s1.votes = 0; s1.year = 2005
        val expected = SongsRespond(Songs(listOf(s1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testScan() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.Scan\", \"params\": {\"directory\":\"/path/to/directory\",\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.scan(KodiID(1), KodiPath("/path/to/directory"))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testSetAlbumDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.SetAlbumDetails\", \"params\": {\"albumid\":6,\"title\":\"t1\",\"artist\":[\"A1\",\"A2\"],\"description\":\"d1\",\"genre\":[\"Rock\"],\"theme\":[\"t1\"],\"mood\":[\"m1\",\"m2\"],\"style\":[\"s1\"],\"type\":\"t1\",\"albumlabel\":\"al1\",\"rating\":1.0,\"year\":2016,\"userrating\":5,\"votes\":3}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setAlbumDetails(KodiID(1), com.cf.jkodiwrapper.methods.audio.params.entity.AlbumDetails(LibraryID(6), "t1", listOf("A1", "A2"), "d1", listOf("Rock"), listOf("t1"), listOf("m1", "m2"),
                listOf("s1"), "t1", "al1", 1.0, 2016, 5, 3))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testSetArtistDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.SetArtistDetails\", \"params\": {\"artistid\":2,\"artist\":\"A1\",\"instrument\":[\"I1\"],\"style\":[\"s1\"],\"mood\":[\"m1\"],\"born\":\"1.1.1970\",\"formed\":\"1.1.1990\",\"description\":\"d1\",\"genre\":[\"Rock\"],\"died\":\"1.1.2020\",\"disbanded\":\"1.1.2010\",\"yearsactive\":[\"2000\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setArtistDetails(KodiID(1), com.cf.jkodiwrapper.methods.audio.params.entity.ArtistDetails(LibraryID(2), "A1", listOf("I1"), listOf("s1"), listOf("m1"), "1.1.1970", "1.1.1990", "d1",
                listOf("Rock"), "1.1.2020", "1.1.2010", listOf("2000")))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testSetSongDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"AudioLibrary.SetSongDetails\", \"params\": {\"songid\":1,\"title\":\"t1\",\"artist\":[\"A1\"],\"albumartist\":[\"AA1\"],\"genre\":[\"Rock\"],\"year\":2018,\"rating\":1.0,\"album\":\"a1\",\"track\":99,\"disc\":2,\"duration\":300,\"comment\":\"c1\",\"musicbrainztrackid\":\"mbt1\",\"musicbrainzartistid\":\"mbt1\",\"musicbrainzalbumid\":\"mbalid\",\"musicbrainzalbumartistid\":\"mbalid\",\"playcount\":4,\"lastplayed\":\"1.1.1970\",\"userrating\":5,\"votes\":3}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setSongDetails(KodiID(1), com.cf.jkodiwrapper.methods.audio.params.entity.SongDetails(LibraryID(1), "t1", listOf("A1"), listOf("AA1"), listOf("Rock"), 2018, 1.0, "a1", 99,
                2, 300, "c1", "mbt1", "mbaid", "mbalid", "mbalidd", 4, "1.1.1970",
                5, 3))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun createAlbumFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"album\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterAlbums.getAndFilter(listOf(
                ListFilterAlbums.getOrFilter(listOf(
                        ListFilterAlbums.getRuleFilter(
                                ListFilterRuleAlbums(ListFilterFieldsAlbums.ALBUM, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }

    @Test
    fun createArtistFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"artist\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterArtists.getAndFilter(listOf(
                ListFilterArtists.getOrFilter(listOf(
                        ListFilterArtists.getRuleFilter(
                                ListFilterRuleArtists(ListFilterFieldsArtists.ARTIST, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }

    @Test
    fun createSongFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"genre\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterSongs.getAndFilter(listOf(
                ListFilterSongs.getOrFilter(listOf(
                        ListFilterSongs.getRuleFilter(
                                ListFilterRuleSongs(ListFilterFieldsSongs.GENRE, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }
}