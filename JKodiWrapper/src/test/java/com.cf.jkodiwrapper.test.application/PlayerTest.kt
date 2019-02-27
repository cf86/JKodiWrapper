package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.player.KodiPlayer
import com.cf.jkodiwrapper.methods.player.respond.*
import com.cf.jkodiwrapper.methods.player.respond.entity.Player
import com.cf.jkodiwrapper.methods.player.respond.entity.PlayerItem
import com.cf.jkodiwrapper.methods.player.respond.entity.SeekToReturn
import com.cf.jkodiwrapper.types.global.GlobalDirection
import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement
import com.cf.jkodiwrapper.types.global.GlobalNxtPrev
import com.cf.jkodiwrapper.types.global.GlobalTime
import com.cf.jkodiwrapper.types.list.ListFieldsAll
import com.cf.jkodiwrapper.types.list.ListItemAll
import com.cf.jkodiwrapper.types.media.MediaArtwork
import com.cf.jkodiwrapper.types.player.*
import com.cf.jkodiwrapper.types.playlist.PlaylistPosition
import com.cf.jkodiwrapper.types.subtitle.SubtitleOperation
import com.cf.jkodiwrapper.types.video.VideoResume
import com.cf.jkodiwrapper.types.video.VideoStreams
import com.cf.jkodiwrapper.types.video.entity.Audio
import com.cf.jkodiwrapper.types.video.entity.Video
import com.cf.jkodiwrapper.util.RequestRespond
import com.cf.jkodiwrapper.util.RequestUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(RequestUtil::class)
@PowerMockIgnore("javax.net.ssl.*")
class PlayerTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiPlayer("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Player.Open\", \"params\": {\"item\":{\"file\":\"/path/to/file\"},\"options\":{}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.openPlayer(KodiID(5), KodiPath("/path/to/file"))
        val expected = KodiStringRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testOpenPlayer() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Open\", \"params\": {\"item\":{\"file\":\"/path/to/file\"},\"options\":{}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.openPlayer(KodiID(1), KodiPath("/path/to/file"))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testPlayPause() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"speed\":1}} "
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.PlayPause\", \"params\": {\"playerid\":1,\"play\":\"toggle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.playPause(KodiID(1), PlayerID(1))
        val expected = PlayPauseSpeedRespond(PlayerSpeed(1))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testStopPlayer() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Stop\", \"params\": {\"playerid\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.stopPlayer(KodiID(1), PlayerID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetActivePlayers() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":[{\"playerid\":1,\"type\":\"video\"}]}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.GetActivePlayers\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getActivePlayers(KodiID(1))
        val expected = PlayersRespond(listOf(Player(playerid = 1, type = "video")))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetPlayers() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":[{\"name\":\"VideoPlayer\",\"playsaudio\":true,\"playsvideo\":true,\"type\":\"video\"},{\"name\":\"PAPlayer\",\"playsaudio\":true,\"playsvideo\":false,\"type\":\"music\"}]}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.GetPlayers\", \"params\": {\"media\":\"all\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getPlayers(KodiID(1))
        val p1 = Player("VideoPlayer", null, true, true, "video")
        val p2 = Player("PAPlayer", null, true, false, "music")
        val expected = PlayersRespond(listOf(p1, p2))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetPlayerItem() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"item\":{\"album\":\"\",\"art\":{},\"artist\":[],\"cast\":[],\"country\":[],\"dateadded\":\"\",\"director\":[],\"episode\":-1,\"episodeguide\":\"\",\"fanart\":\"\",\"file\":\"http://upnp/%25/62696EF5EB0A713E05BD988F205A9EBE/movie.mkv\",\"firstaired\":\"\",\"genre\":[],\"imdbnumber\":\"\",\"label\":\"movie.mkv\",\"lastplayed\":\"2018-06-16 20:06:29\",\"mpaa\":\"\",\"originaltitle\":\"\",\"playcount\":1,\"plot\":\"\",\"plotoutline\":\"\",\"premiered\":\"\",\"productioncode\":\"\",\"rating\":0,\"resume\":{\"position\":0,\"total\":0},\"runtime\":8037,\"season\":-1,\"set\":\"\",\"setid\":-1,\"showlink\":[],\"showtitle\":\"\",\"sorttitle\":\"\",\"specialsortepisode\":-1,\"specialsortseason\":-1,\"streamdetails\":{\"audio\":[{\"channels\":6,\"codec\":\"ac3\",\"language\":\"\"}],\"subtitle\":[],\"video\":[{\"aspect\":2.3684210777282714844,\"codec\":\"mpeg4\",\"duration\":8037,\"height\":304,\"language\":\"\",\"stereomode\":\"\",\"width\":720}]},\"studio\":[],\"tag\":[],\"tagline\":\"\",\"thumbnail\":\"\",\"title\":\"\",\"top250\":0,\"track\":-1,\"trailer\":\"\",\"tvshowid\":-1,\"type\":\"unknown\",\"userrating\":0,\"votes\":\"0\",\"writer\":[],\"year\":1601}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.getItem\", \"params\": {\"playerid\":1,\"properties\":[\"album\",\"albumartist\",\"albumartistid\",\"albumid\",\"albumlabel\",\"albumreleasetype\",\"art\",\"artist\",\"artistid\",\"cast\",\"channel\",\"channelnumber\",\"channeltype\",\"comment\",\"compilation\",\"contributors\",\"country\",\"dateadded\",\"description\",\"director\",\"disc\",\"displayartist\",\"displaycomposer\",\"displayconductor\",\"displaylyricist\",\"displayorchestra\",\"duration\",\"endtime\",\"episode\",\"episodeguide\",\"fanart\",\"file\",\"firstaired\",\"genre\",\"genreid\",\"hidden\",\"imdbnumber\",\"lastplayed\",\"locked\",\"lyrics\",\"mood\",\"mpaa\",\"musicbrainzalbumartistid\",\"musicbrainzalbumid\",\"musicbrainzartistid\",\"musicbrainztrackid\",\"originaltitle\",\"playcount\",\"plot\",\"plotoutline\",\"premiered\",\"productioncode\",\"rating\",\"releasetype\",\"resume\",\"runtime\",\"season\",\"set\",\"setid\",\"showlink\",\"showtitle\",\"sorttitle\",\"specialsortepisode\",\"specialsortseason\",\"starttime\",\"streamdetails\",\"studio\",\"style\",\"tag\",\"tagline\",\"theme\",\"thumbnail\",\"title\",\"top250\",\"track\",\"trailer\",\"tvshowid\",\"uniqueid\",\"userrating\",\"votes\",\"watchedepisodes\",\"writer\",\"year\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getPlayerItem(KodiID(1), PlayerID(1), ListFieldsAll.getAllFields())
        val item = ListItemAll(null, 0, "tv", null, false, false, null)
        item.album = "";item.art = MediaArtwork();item.dateadded = "";item.episode = -1
        item.episodeguide = "";item.fanart = "";item.file = "http://upnp/%25/62696EF5EB0A713E05BD988F205A9EBE/movie.mkv"
        item.firstaired = "";item.imdbnumber = "";item.label = "movie.mkv";item.lastplayed = "2018-06-16 20:06:29";item.mpaa = ""
        item.originaltitle = "";item.playcount = 1;item.plot = "";item.plotoutline = "";item.premiered = "";item.productioncode = "";item.rating = 0.0;item.resume = VideoResume(0, 0)
        item.runtime = 8037;item.season = -1;item.set = "";item.setid = -1;item.showtitle = "";item.sorttitle = "";item.specialsortepisode = -1
        item.specialsortseason = -1;item.streamdetails = VideoStreams(listOf(Audio(6, "ac3", "")), ArrayList(), listOf(Video(2.3684210777282714844, "mpeg4", 8037, 304, 720)))
        item.tagline = "";item.thumbnail = "";item.title = "";item.top250 = 0;item.track = -1;item.trailer = "";item.tvshowid = -1;item.type = "unknown"
        item.userrating = 0;item.votes = "0";item.year = 1601
        val expected = PlayerItemRespond(PlayerItem(item))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"audiostreams\":[{\"bitrate\":446087,\"channels\":6,\"codec\":\"ac3\",\"index\":0,\"language\":\"ger\",\"name\":\"AC3 5.1\"}],\"canchangespeed\":true,\"canmove\":false,\"canrepeat\":true,\"canrotate\":false,\"canseek\":true,\"canshuffle\":true,\"canzoom\":false,\"currentaudiostream\":{\"bitrate\":446087,\"channels\":6,\"codec\":\"ac3\",\"index\":0,\"language\":\"ger\",\"name\":\"AC3 5.1\"},\"currentsubtitle\":{\"index\":0,\"language\":\"ger\",\"name\":\"GER Full\"},\"currentvideostream\":{\"codec\":\"h264\",\"height\":534,\"index\":0,\"language\":\"\",\"name\":\"\",\"width\":1280},\"live\":false,\"partymode\":false,\"percentage\":0.13991507887840270996,\"playlistid\":1,\"position\":0,\"repeat\":\"off\",\"shuffled\":false,\"speed\":1,\"subtitleenabled\":false,\"subtitles\":[{\"index\":0,\"language\":\"ger\",\"name\":\"GER Full\"},{\"index\":1,\"language\":\"eng\",\"name\":\"ENG Full\"}],\"time\":{\"hours\":0,\"milliseconds\":129,\"minutes\":0,\"seconds\":9},\"totaltime\":{\"hours\":1,\"milliseconds\":672,\"minutes\":48,\"seconds\":44},\"type\":\"video\",\"videostreams\":[{\"codec\":\"h264\",\"height\":534,\"index\":0,\"language\":\"\",\"name\":\"\",\"width\":1280}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.GetProperties\", \"params\": {\"playerid\":1,\"properties\":[\"audiostreams\",\"canchangespeed\",\"canmove\",\"canrepeat\",\"canrotate\",\"canseek\",\"canshuffle\",\"canzoom\",\"currentaudiostream\",\"currentsubtitle\",\"currentvideostream\",\"live\",\"partymode\",\"percentage\",\"playlistid\",\"position\",\"repeat\",\"shuffled\",\"speed\",\"subtitleenabled\",\"subtitles\",\"time\",\"totaltime\",\"type\",\"videostreams\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getPlayerProperties(KodiID(1), PlayerID(1), PlayerPropertyName.getAllFields())
        val item = PlayerPropertyValue()
        item.audiostreams = listOf(PlayerAudioStream(446087, 6, "ac3", 0, "ger", "AC3 5.1"))
        item.canchangespeed = true;item.canmove = false;item.canrepeat = true;item.canrotate = false;item.canseek = true;item.canshuffle = true;item.canzoom = false
        item.currentaudiostream = PlayerAudioStream(446087, 6, "ac3", 0, "ger", "AC3 5.1")
        item.currentsubtitle = PlayerSubtitle(0, "ger", "GER Full")
        item.currentvideostream = PlayerVideoStream("h264", 534, 0, "", "", 1280)
        item.live = false;item.partymode = false;item.percentage = 0.13991507887840270996;item.playlistid = 1;item.position = 0;item.repeat = "off"
        item.shuffled = false;item.speed = 1;item.subtitleenabled = false
        item.subtitles = listOf(PlayerSubtitle(0, "ger", "GER Full"), PlayerSubtitle(1, "eng", "ENG Full"))
        item.time = GlobalTime(0, 0, 9, 129)
        item.totaltime = GlobalTime(1, 48, 44, 672);item.type = "video"
        item.videostreams = listOf(PlayerVideoStream("h264", 534, 0, "", "", 1280))

        val expected = PlayerPropertiesRespond(item)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGoToPlayListPos() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.goTo\", \"params\": {\"playerid\":1,\"to\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.goTo(KodiID(1), PlayerID(1), PlaylistPosition(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGoToNextItem() {
        val respond = "{\"id\":2,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"Player.goTo\", \"params\": {\"playerid\":2,\"to\":\"next\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.goTo(KodiID(2), PlayerID(1), GlobalNxtPrev.NEXT)
        val expected = KodiStringRespond("OK")
        expected.id = 2
        assertEquals(expected, actual)
    }

    @Test
    fun testMove() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Move\", \"params\": {\"playerid\":1,\"direction\":\"right\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.move(KodiID(1), PlayerID(1), GlobalDirection.RIGHT)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testRotatePicture() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Rotate\", \"params\": {\"playerid\":1,\"value\":\"clockwise\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.rotatePicture(KodiID(1), PlayerID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSeekToPercent() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"percentage\":56.11011505126953125,\"time\":{\"hours\":1,\"milliseconds\":1,\"minutes\":1,\"seconds\":1},\"totaltime\":{\"hours\":1,\"milliseconds\":672,\"minutes\":48,\"seconds\":44}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Seek\", \"params\": {\"playerid\":1,\"value\":56.11011505126953}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.seek(KodiID(1), PlayerID(1), PlayerPositionPercentage(56.11011505126953125))
        val expected = SeekToRespond(SeekToReturn(56.11011505126953125, GlobalTime(1, 1, 1, 1), GlobalTime(1, 48, 44, 672)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSeekToTime() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"percentage\":56.11011505126953125,\"time\":{\"hours\":1,\"milliseconds\":1,\"minutes\":1,\"seconds\":1},\"totaltime\":{\"hours\":1,\"milliseconds\":672,\"minutes\":48,\"seconds\":44}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Seek\", \"params\": {\"playerid\":1,\"value\":{\"hours\":1,\"minutes\":1,\"seconds\":1,\"milliseconds\":1}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.seek(KodiID(1), PlayerID(1), PlayerPositionTime(1, 1, 1, 1))
        val expected = SeekToRespond(SeekToReturn(56.11011505126953125, GlobalTime(1, 1, 1, 1), GlobalTime(1, 48, 44, 672)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSeekToForward() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"percentage\":56.11011505126953125,\"time\":{\"hours\":1,\"milliseconds\":1,\"minutes\":1,\"seconds\":1},\"totaltime\":{\"hours\":1,\"milliseconds\":672,\"minutes\":48,\"seconds\":44}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Seek\", \"params\": {\"playerid\":1,\"value\":\"bigforward\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.seek(KodiID(1), PlayerID(1), PlayerForwBackw.BIG_FORWARD)
        val expected = SeekToRespond(SeekToReturn(56.11011505126953125, GlobalTime(1, 1, 1, 1), GlobalTime(1, 48, 44, 672)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetAudioStreamIndex() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetAudioStream\", \"params\": {\"playerid\":1,\"stream\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setAudioStream(KodiID(1), PlayerID(1), 1)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetAudioStreamNext() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetAudioStream\", \"params\": {\"playerid\":1,\"stream\":\"next\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setAudioStream(KodiID(1), PlayerID(1), GlobalNxtPrev.NEXT)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testPartyMode() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetPartymode\", \"params\": {\"playerid\":1,\"partymode\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setPartyMode(KodiID(1), PlayerID(1), false)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testPartyModeToggle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetPartymode\", \"params\": {\"playerid\":1,\"partymode\":\"toggle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setPartyMode(KodiID(1), PlayerID(1), null)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testRepeat() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetRepeat\", \"params\": {\"playerid\":1,\"repeat\":\"all\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setRepeat(KodiID(1), PlayerID(1), PlayerRepeat.ALL)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testRepeatCycle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetRepeat\", \"params\": {\"playerid\":1,\"repeat\":\"cycle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setRepeat(KodiID(1), PlayerID(1), null)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testShuffle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetShuffle\", \"params\": {\"playerid\":1,\"shuffle\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setShuffle(KodiID(1), PlayerID(1), true)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testShuffleToggle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetShuffle\", \"params\": {\"playerid\":1,\"shuffle\":\"toggle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setShuffle(KodiID(1), PlayerID(1), null)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetSpeed() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"speed\":4}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetSpeed\", \"params\": {\"playerid\":1,\"speed\":4}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setSpeed(KodiID(1), PlayerID(1), PlayerSpeed.SPEED_4)
        val expected = PlayPauseSpeedRespond(PlayerSpeed.SPEED_4)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetSpeedIncrement() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"speed\":4}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetSpeed\", \"params\": {\"playerid\":1,\"speed\":\"increment\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setSpeed(KodiID(1), PlayerID(1), GlobalIncrementDecrement.INCREMENT)
        val expected = PlayPauseSpeedRespond(PlayerSpeed.SPEED_4)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetSubtitleIndex() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetSubtitle\", \"params\": {\"playerid\":1,\"subtitle\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setSubtitle(KodiID(1), PlayerID(1), 1)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetSubtitleNext() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetSubtitle\", \"params\": {\"playerid\":1,\"subtitle\":\"next\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setSubtitle(KodiID(1), PlayerID(1), SubtitleOperation.NEXT)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetVideoStreamIndex() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetVideoStream\", \"params\": {\"playerid\":1,\"stream\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setVideoStream(KodiID(1), PlayerID(1), 1)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetVideoStreamNext() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.SetVideoStream\", \"params\": {\"playerid\":1,\"stream\":\"next\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setVideoStream(KodiID(1), PlayerID(1), GlobalNxtPrev.NEXT)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testZoomIn() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Zoom\", \"params\": {\"playerid\":1,\"zoom\":\"in\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.zoomPicture(KodiID(1), PlayerID(1), PlayerZoomOperation.IN)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testZoomLevel() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Player.Zoom\", \"params\": {\"playerid\":1,\"zoom\":5}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.zoomPicture(KodiID(1), PlayerID(1), 5)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}