package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.files.FilesMethod
import com.cf.jkodiwrapper.methods.files.KodiFiles
import com.cf.jkodiwrapper.methods.files.params.post.DirectoryParam
import com.cf.jkodiwrapper.methods.files.params.post.SourceParam
import com.cf.jkodiwrapper.methods.files.respond.DirectoryRespond
import com.cf.jkodiwrapper.methods.files.respond.FileDetailsRespond
import com.cf.jkodiwrapper.methods.files.respond.PrepareDownloadRespond
import com.cf.jkodiwrapper.methods.files.respond.SourcesRespond
import com.cf.jkodiwrapper.methods.files.respond.entity.FileDetails
import com.cf.jkodiwrapper.methods.files.respond.entity.Files
import com.cf.jkodiwrapper.methods.files.respond.entity.PreparedDownload
import com.cf.jkodiwrapper.methods.files.respond.entity.Sources
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.list.*
import com.cf.jkodiwrapper.types.media.MediaArtwork
import com.cf.jkodiwrapper.types.video.VideoResume
import com.cf.jkodiwrapper.util.RequestRespond
import com.cf.jkodiwrapper.util.RequestUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.time.LocalDateTime

@RunWith(PowerMockRunner::class)
@PrepareForTest(RequestUtil::class)
@PowerMockIgnore("javax.net.ssl.*")
class FilesTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiFiles("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = KodiPostData(KodiID(1), FilesMethod.GET_SOURCES, SourceParam(FilesMedia.FILES, ListSort(), ListLimits()))
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post.toJsonString(), header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.getSources(KodiID(1), FilesMedia.FILES)
        val expected = SourcesRespond()
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testGetDirectories() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"files\":[{\"art\":{},\"fanart\":\"\",\"file\":\"/path/to/file1\",\"filetype\":\"directory\",\"label\":\"Label1\",\"lastmodified\":\"2017-06-11 20:09:10\",\"mimetype\":\"x-directory/normal\",\"size\":0,\"thumbnail\":\"\",\"title\":\"\",\"type\":\"unknown\"},{\"art\":{},\"fanart\":\"\",\"file\":\"/path/to/file2\",\"filetype\":\"directory\",\"label\":\"Label2\",\"lastmodified\":\"2017-03-17 23:32:10\",\"mimetype\":\"x-directory/normal\",\"size\":0,\"thumbnail\":\"\",\"title\":\"\",\"type\":\"unknown\"}],\"limits\":{\"end\":2,\"start\":0,\"total\":2}}}"
        val post = KodiPostData(KodiID(1), FilesMethod.GET_DIRECTORY, DirectoryParam(KodiPath("/path/to/dir"), FilesMedia.FILES, ArrayList(), ListSort(), ListLimits()))
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post.toJsonString(), header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getDirectories(KodiID(1), KodiPath("/path/to/dir"), FilesMedia.FILES, listOf(), ListSort(), ListLimits())
        val f1 = ListItemFile("directory", "2017-06-11 20:09:10", "x-directory/normal", 0)
        f1.thumbnail = ""
        f1.title = ""
        f1.type = "unknown"
        f1.label = "Label1"
        f1.file = "/path/to/file1"
        f1.art = MediaArtwork()
        f1.fanart = ""
        val f2 = ListItemFile("directory", "2017-03-17 23:32:10", "x-directory/normal", 0)
        f2.thumbnail = ""
        f2.title = ""
        f2.type = "unknown"
        f2.label = "Label2"
        f2.file = "/path/to/file2"
        f2.art = MediaArtwork()
        f2.fanart = ""
        val expected = DirectoryRespond(Files(listOf(f1, f2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetFileDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"filedetails\":{\"art\":{\"banner\":\"banner\",\"fanart\":\"fa\",\"poster\":\"p\"},\"fanart\":\"\",\"filetype\":\"unknown\",\"file\":\"/path/to/file\",\"label\":\"label1\",\"lastmodified\":\"2015-11-22 12:30:16\",\"mimetype\":\"video/x-matroska\",\"size\":4676820172,\"thumbnail\":\"thumb\",\"title\":\"title\",\"type\":\"unknown\",\"year\":2015,\"comment\":\"a comment\",\"country\":[\"c1\",\"c2\"],\"duration\":1234234,\"episode\":1,\"originaltitle\":\"orig Titel\",\"tag\":[\"t1\",\"t2\"],\"trailer\":\"trailer\",\"director\":[\"d1\",\"d2\"],\"runtime\":55,\"lastplayed\":\"2017-11-22 12:30:16\",\"plot\":\"plot\"}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Files.GetFileDetails\", \"params\": {\"file\": \"/path/to/file\",\"media\":\"files\",\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumlabel\",\"art\",\"artist\",\"artistid\",\"cast\",\"comment\",\"country\",\"dateadded\",\"description\",\"director\",\"disc\",\"displayartist\",\"duration\",\"episode\",\"episodeguide\",\"fanart\",\"file\",\"firstaired\",\"genre\",\"genreid\",\"imdbnumber\",\"lastmodified\",\"lastplayed\",\"lyrics\",\"mimetype\",\"mood\",\"mpaa\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"originaltitle\",\"playcount\",\"plot\",\"plotoutline\",\"premiered\",\"productioncode\",\"rating\",\"resume\",\"runtime\",\"season\",\"set\",\"setid\",\"showlink\",\"showtitle\",\"size\",\"sorttitle\",\"specialsortepisode\",\"specialsortseason\",\"streamdetails\",\"studio\",\"style\",\"tag\",\"tagline\",\"theme\",\"thumbnail\",\"title\",\"top250\",\"track\",\"trailer\",\"tvshowid\",\"uniqueid\",\"votes\",\"watchedepisodes\",\"writer\",\"year\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getFileDetails(KodiID(1), KodiPath("/path/to/file"), FilesMedia.FILES, ListFieldsFiles.getAllFields())
        val f = ListItemFile("unknown", "2015-11-22 12:30:16", "video/x-matroska", 4676820172)
        f.fanart = ""
        f.art = MediaArtwork("banner", "fa", "p")
        f.file = "/path/to/file"
        f.label = "label1"
        f.thumbnail = "thumb"
        f.title = "title"
        f.type = "unknown"
        f.year = 2015
        f.comment = "a comment"
        f.country = listOf("c1", "c2")
        f.duration = 1234234
        f.episode = 1
        f.originaltitle = "orig Titel"
        f.tag = listOf("t1", "t2")
        f.trailer = "trailer"
        f.director = listOf("d1", "d2")
        f.runtime = 55
        f.lastplayed = "2017-11-22 12:30:16"
        f.plot = "plot"

        val expected = FileDetailsRespond(FileDetails(f))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetSources() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":2,\"start\":0,\"total\":2},\"sources\":[{\"file\":\"upnp://5b3613aa28414d43bfe6eb322fa98378/f137a2dd21bbc1b99aa5c0f6bf02a805/\",\"label\":\"Emby123\"},{\"file\":\"/path/to/file\",\"label\":\"movies\"}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Files.GetSources\", \"params\": {\"media\":\"files\",\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getSources(KodiID(1), FilesMedia.FILES, ListSort(), ListLimits())
        val s1 = ListItemsSources("upnp://5b3613aa28414d43bfe6eb322fa98378/f137a2dd21bbc1b99aa5c0f6bf02a805/", "Emby123")
        val s2 = ListItemsSources("/path/to/file", "movies")
        val expected = SourcesRespond(Sources(ListLimitsReturned(2, 0, 2), listOf(s1, s2)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testPrepareDownload() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"details\":{\"path\":\"vfs/E%3a%5cpath%5cto%5cfile\"},\"mode\":\"redirect\",\"protocol\":\"http\"}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Files.PrepareDownload\", \"params\": {\"path\":\"/path/to/file\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.prepareDownload(KodiID(1), KodiPath("/path/to/file"))
        val details = LinkedHashMap<String, String>()
        details["path"] = "vfs/E%3a%5cpath%5cto%5cfile"
        val expected = PrepareDownloadRespond(PreparedDownload(details, "redirect", "http"))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun setFileDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Files.SetFileDetails\", \"params\": {\"file\":\"/path/to/file\",\"media\":\"video\",\"playcount\":\"0\",\"lastplayed\":\"2017-06-11 20:09:10\",\"resume\":{\"position\":0,\"total\":0}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setFileDetails(KodiID(1), KodiPath("/path/to/file"), FilesMedia.VIDEO, 0, LocalDateTime.of(2017, 11, 6, 20, 9, 10), VideoResume())
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}