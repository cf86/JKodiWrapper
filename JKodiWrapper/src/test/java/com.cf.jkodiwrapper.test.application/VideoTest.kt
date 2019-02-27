package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.video.KodiVideo
import com.cf.jkodiwrapper.methods.video.respond.*
import com.cf.jkodiwrapper.methods.video.respond.entity.*
import com.cf.jkodiwrapper.types.library.LibraryDetailsGenre
import com.cf.jkodiwrapper.types.library.LibraryFieldsGenre
import com.cf.jkodiwrapper.types.library.LibraryID
import com.cf.jkodiwrapper.types.list.*
import com.cf.jkodiwrapper.types.media.MediaArtwork
import com.cf.jkodiwrapper.types.video.*
import com.cf.jkodiwrapper.types.video.entity.Audio
import com.cf.jkodiwrapper.types.video.entity.Subtitle
import com.cf.jkodiwrapper.types.video.entity.Video
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
class VideoTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiVideo("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"VideoLibrary.Scan\", \"params\": {\"directory\":\"C:\\\\path\\\\to\\\\dir\",\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.scan(KodiID(5), KodiPath("C:\\\\path\\\\to\\\\dir"), true)
        val expected = KodiStringRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testScan() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"VideoLibrary.Scan\", \"params\": {\"directory\":\"C:\\\\path\\\\to\\\\dir\",\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.scan(KodiID(5), KodiPath("C:\\\\path\\\\to\\\\dir"), true)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testClean() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.Clean\", \"params\": {\"showdialogs\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.clean(KodiID(1), true)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetMovies() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":1,\"start\":0,\"total\":1},\"movies\":[{\"art\":{\"fanart\":\"image://path/to/image1.jpg\",\"poster\":\"image://path/to/image2.jpg\"},\"cast\":[{\"name\":\"C1\",\"order\":0,\"role\":\"R1\",\"thumbnail\":\"image://path/to/image3.jpg\"},{\"name\":\"C2\",\"order\":1,\"role\":\"R2\",\"thumbnail\":\"image://path/to/image4.jpg\"}],\"country\":[\"Germany\"],\"dateadded\":\"2018-04-25 17:51:47\",\"director\":[\"D1\"],\"fanart\":\"image://path/to/image5.jpg\",\"file\":\"/path/to/file.mkv\",\"genre\":[\"Action\",\"Science Fiction\"],\"imdbnumber\":\"u1\",\"label\":\"Movie 1\",\"lastplayed\":\"2018-08-04 09:01:32\",\"movieid\":1,\"mpaa\":\"Rated PG-13\",\"originaltitle\":\"Movie 1\",\"playcount\":0,\"plot\":\"Plot 1\",\"plotoutline\":\"\",\"premiered\":\"2018-01-01\",\"rating\":2.0,\"ratings\":{\"themoviedb\":{\"default\":true,\"rating\":2.0,\"votes\":42}},\"resume\":{\"position\":0,\"total\":0},\"runtime\":8632,\"set\":\"Set 1\",\"setid\":1,\"showlink\":[],\"sorttitle\":\"\",\"streamdetails\":{\"audio\":[{\"channels\":6,\"codec\":\"dca\",\"language\":\"ger\"}],\"subtitle\":[{\"language\":\"ger\"}],\"video\":[{\"aspect\":2.4000000953674316406,\"codec\":\"h264\",\"duration\":8632,\"height\":800,\"language\":\"\",\"stereomode\":\"\",\"width\":1920}]},\"studio\":[\"Studio 1\"],\"tag\":[],\"tagline\":\"Tag1\",\"thumbnail\":\"image://path/to/image6.jpg\",\"title\":\"Title1\",\"top250\":0,\"trailer\":\"plugin://trailer1\",\"uniqueid\":{\"imdb\":\"t1\",\"tmdb\":\"t2\"},\"userrating\":2,\"votes\":\"42\",\"writer\":[\"w1\"],\"year\":2018}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.GetMovies\", \"params\": {\"properties\":[\"art\",\"cast\",\"country\",\"dateadded\",\"director\",\"fanart\",\"file\",\"genre\",\"imdbnumber\",\"lastplayed\",\"mpaa\",\"originaltitle\",\"playcount\",\"plot\",\"plotoutline\",\"premiered\",\"rating\",\"ratings\",\"resume\",\"runtime\",\"set\",\"setid\",\"showlink\",\"sorttitle\",\"streamdetails\",\"studio\",\"tag\",\"tagline\",\"thumbnail\",\"title\",\"top250\",\"trailer\",\"uniqueid\",\"userrating\",\"votes\",\"writer\",\"year\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getMovies(KodiID(1), VideoFieldsMovie.getAllFields())
        val m1 = VideoDetailsMovie(listOf(VideoCast("C1", 0, "R1", "image://path/to/image3.jpg"),
                VideoCast("C2", 1, "R2", "image://path/to/image4.jpg")), listOf("Germany"), listOf("Action", "Science Fiction"),
                "u1", 1, "Rated PG-13", "Movie 1", "", "2018-01-01", 2.0, "Set 1", 1, ArrayList(),
                "", listOf("Studio 1"), ArrayList(), "Tag1", 0, "plugin://trailer1", 2, "42", listOf("w1"), 2018)
        m1.thumbnail = "image://path/to/image6.jpg"; m1.title = "Title1"; m1.art = MediaArtwork(fanart = "image://path/to/image1.jpg", poster = "image://path/to/image2.jpg")
        m1.dateadded = "2018-04-25 17:51:47"; m1.director = listOf("D1"); m1.fanart = "image://path/to/image5.jpg"; m1.file = "/path/to/file.mkv"; m1.label = "Movie 1"
        m1.lastplayed = "2018-08-04 09:01:32"; m1.playcount = 0; m1.plot = "Plot 1"; m1.runtime = 8632; m1.resume = VideoResume(0, 0)
        m1.streamdetails = VideoStreams(listOf(Audio(6, "dca", "ger")), listOf(Subtitle("ger")),
                listOf(Video(2.4000000953674316406, "h264", 8632, 800, 1920)))
        val expected = MoviesRespond(Movies(listOf(m1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetMovieDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"moviedetails\":{\"art\":{\"fanart\":\"image://path/to/image1.jpg\",\"poster\":\"image://path/to/image2.jpg\"},\"cast\":[{\"name\":\"C1\",\"order\":0,\"role\":\"R1\",\"thumbnail\":\"image://path/to/image3.jpg\"},{\"name\":\"C2\",\"order\":1,\"role\":\"R2\",\"thumbnail\":\"image://path/to/image4.jpg\"}],\"country\":[\"Germany\"],\"dateadded\":\"2018-04-25 17:51:47\",\"director\":[\"D1\"],\"fanart\":\"image://path/to/image5.jpg\",\"file\":\"/path/to/file.mkv\",\"genre\":[\"Action\",\"Science Fiction\"],\"imdbnumber\":\"u1\",\"label\":\"Movie 1\",\"lastplayed\":\"2018-08-04 09:01:32\",\"movieid\":1,\"mpaa\":\"Rated PG-13\",\"originaltitle\":\"Movie 1\",\"playcount\":0,\"plot\":\"Plot 1\",\"plotoutline\":\"\",\"premiered\":\"2018-01-01\",\"rating\":2.0,\"ratings\":{\"themoviedb\":{\"default\":true,\"rating\":2.0,\"votes\":42}},\"resume\":{\"position\":0,\"total\":0},\"runtime\":8632,\"set\":\"Set 1\",\"setid\":1,\"showlink\":[],\"sorttitle\":\"\",\"streamdetails\":{\"audio\":[{\"channels\":6,\"codec\":\"dca\",\"language\":\"ger\"}],\"subtitle\":[{\"language\":\"ger\"}],\"video\":[{\"aspect\":2.4000000953674316406,\"codec\":\"h264\",\"duration\":8632,\"height\":800,\"language\":\"\",\"stereomode\":\"\",\"width\":1920}]},\"studio\":[\"Studio 1\"],\"tag\":[],\"tagline\":\"Tag1\",\"thumbnail\":\"image://path/to/image6.jpg\",\"title\":\"Title1\",\"top250\":0,\"trailer\":\"plugin://trailer1\",\"uniqueid\":{\"imdb\":\"t1\",\"tmdb\":\"t2\"},\"userrating\":2,\"votes\":\"42\",\"writer\":[\"w1\"],\"year\":2018}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.GetMovieDetails\", \"params\": {\"movieid\":1,\"properties\":[\"art\",\"cast\",\"country\",\"dateadded\",\"director\",\"fanart\",\"file\",\"genre\",\"imdbnumber\",\"lastplayed\",\"mpaa\",\"originaltitle\",\"playcount\",\"plot\",\"plotoutline\",\"premiered\",\"rating\",\"ratings\",\"resume\",\"runtime\",\"set\",\"setid\",\"showlink\",\"sorttitle\",\"streamdetails\",\"studio\",\"tag\",\"tagline\",\"thumbnail\",\"title\",\"top250\",\"trailer\",\"uniqueid\",\"userrating\",\"votes\",\"writer\",\"year\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getMovieDetails(KodiID(1), LibraryID(1), VideoFieldsMovie.getAllFields())
        val m1 = VideoDetailsMovie(listOf(VideoCast("C1", 0, "R1", "image://path/to/image3.jpg"),
                VideoCast("C2", 1, "R2", "image://path/to/image4.jpg")), listOf("Germany"), listOf("Action", "Science Fiction"),
                "u1", 1, "Rated PG-13", "Movie 1", "", "2018-01-01", 2.0, "Set 1", 1, ArrayList(),
                "", listOf("Studio 1"), ArrayList(), "Tag1", 0, "plugin://trailer1", 2, "42", listOf("w1"), 2018)
        m1.thumbnail = "image://path/to/image6.jpg"; m1.title = "Title1"; m1.art = MediaArtwork(fanart = "image://path/to/image1.jpg", poster = "image://path/to/image2.jpg")
        m1.dateadded = "2018-04-25 17:51:47"; m1.director = listOf("D1"); m1.fanart = "image://path/to/image5.jpg"; m1.file = "/path/to/file.mkv"; m1.label = "Movie 1"
        m1.lastplayed = "2018-08-04 09:01:32"; m1.playcount = 0; m1.plot = "Plot 1"; m1.runtime = 8632; m1.resume = VideoResume(0, 0)
        m1.streamdetails = VideoStreams(listOf(Audio(6, "dca", "ger")), listOf(Subtitle("ger")),
                listOf(Video(2.4000000953674316406, "h264", 8632, 800, 1920)))
        val expected = MovieDetailsRespond(MovieDetails(m1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetEpisodes() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"genres\":[{\"genreid\":1,\"label\":\"Action\",\"thumbnail\":\"\",\"title\":\"Action\"},{\"genreid\":2,\"label\":\"Adventure\",\"thumbnail\":\"\",\"title\":\"Adventure\"},{\"genreid\":3,\"label\":\"Comedy\",\"thumbnail\":\"\",\"title\":\"Comedy\"}],\"limits\":{\"end\":3,\"start\":0,\"total\":3}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.getGenres\", \"params\": {\"type\":\"movie\",\"properties\":[\"title\",\"thumbnail\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getGenres(KodiID(1), LibraryFieldsGenre.getAllFields())
        val g1 = LibraryDetailsGenre(1, "", "Action")
        g1.label = "Action"
        val g2 = LibraryDetailsGenre(2, "", "Adventure")
        g2.label = "Adventure"
        val g3 = LibraryDetailsGenre(3, "", "Comedy")
        g3.label = "Comedy"
        val expected = GenresRespond(Genres(listOf(g1, g2, g3), ListLimitsReturned(3, 0, 3)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetMovieSets() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":1,\"start\":0,\"total\":1},\"sets\":[{\"art\":{\"fanart\":\"image://image/to/path1.jpg\",\"poster\":\"image://image/to/path2.jpg\"},\"fanart\":\"image://image/to/path3.jpg\",\"label\":\"L1\",\"playcount\":0,\"plot\":\"A Plot.\",\"setid\":1,\"thumbnail\":\"image://image/to/path4.jpg\",\"title\":\"T1\"}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.GetMovieSets\", \"params\": {\"properties\":[\"title\",\"playcount\",\"fanart\",\"thumbnail\",\"art\",\"plot\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getMovieSets(KodiID(1), VideoFieldsMovieSet.getAllFields())
        val m1 = VideoDetailsMovieSet("A Plot.", 1)
        m1.art = MediaArtwork(fanart = "image://image/to/path1.jpg", poster = "image://image/to/path2.jpg")
        m1.fanart = "image://image/to/path3.jpg"; m1.label = "L1"; m1.playcount = 0; m1.thumbnail = "image://image/to/path4.jpg"; m1.title = "T1"
        val expected = MovieSetsRespond(MovieSets(listOf(m1), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testGetMovieSetDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"setdetails\":{\"art\":{\"fanart\":\"image://path/to/image1.jpg\",\"poster\":\"image://path/to/image2.jpg\"},\"fanart\":\"image://path/to/image3.jpg\",\"label\":\"L1\",\"limits\":{\"end\":1,\"start\":0,\"total\":1},\"movies\":[{\"label\":\"L2\",\"movieid\":2}],\"playcount\":0,\"plot\":\"A Plot.\",\"setid\":1,\"thumbnail\":\"image://path/to/image4.jpg\",\"title\":\"T1\"}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.GetMovieSetDetails\", \"params\": {\"setid\":1,\"properties\":[\"title\",\"playcount\",\"fanart\",\"thumbnail\",\"art\",\"plot\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getMovieSetDetails(KodiID(1), LibraryID(1), VideoFieldsMovieSet.getAllFields())
        val m1 = VideoDetailsMovie()
        m1.label = "L2"; m1.movieid = 2
        val s1 = VideoDetailsMovieSetExtended(ListLimitsReturned(1, 0, 1), listOf(m1))
        s1.art = MediaArtwork(fanart = "image://path/to/image1.jpg", poster = "image://path/to/image2.jpg"); s1.fanart = "image://path/to/image3.jpg"
        s1.label = "L1"; s1.playcount = 0; s1.plot = "A Plot."; s1.setid = 1; s1.thumbnail = "image://path/to/image4.jpg"; s1.title = "T1"
        val expected = MovieSetDetailsRespond(MovieSetDetails(s1))
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRefreshEpisode() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RefreshEpisode\", \"params\": {\"episodeid\":1,\"ignorenfo\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.refreshEpisode(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRefreshMovie() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RefreshMovie\", \"params\": {\"movieid\":1,\"ignorenfo\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.refreshMovie(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRefreshMusicVideo() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RefreshMusicVideo\", \"params\": {\"musicvideoid\":1,\"ignorenfo\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.refreshMusicVideo(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRefreshTvShow() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RefreshTVShow\", \"params\": {\"tvshowid\":1,\"ignorenfo\":false,\"refreshepisodes\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.refreshTvShow(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRemoveEpisode() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RemoveEpisode\", \"params\": {\"episodeid\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.removeEpisode(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRemoveMovie() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RemoveMovie\", \"params\": {\"movieid\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.removeMovie(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRemoveMusicVideo() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"VideoLibrary.RemoveMusicVideo\", \"params\": {\"musicvideoid\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.removeMusicVideo(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testRemoveTVShow() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\" VideoLibrary.RemoveTVShow\", \"params\": {\"tvshowid\":1}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.removeTVShow(KodiID(1), LibraryID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun createMovieFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"actor\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterMovies.getAndFilter(listOf(
                ListFilterMovies.getOrFilter(listOf(
                        ListFilterMovies.getRuleFilter(
                                ListFilterRuleMovies(ListFilterFieldsMovies.ACTOR, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }

    @Test
    fun createEpisodeFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"actor\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterEpisodes.getAndFilter(listOf(
                ListFilterEpisodes.getOrFilter(listOf(
                        ListFilterEpisodes.getRuleFilter(
                                ListFilterRuleEpisodes(ListFilterFieldsEpisodes.ACTOR, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }

    @Test
    fun createMusicVideoFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"album\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterMusicVideos.getAndFilter(listOf(
                ListFilterMusicVideos.getOrFilter(listOf(
                        ListFilterMusicVideos.getRuleFilter(
                                ListFilterRuleMusicVideos(ListFilterFieldsMusicVideos.ALBUM, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }

    @Test
    fun createTvShowFilter() {
        val postExpected = "\"filter\":{\"and\":[{\"or\":[{\"field\":\"actor\",\"operator\":\"contains\",\"value\":\"A1\"}]}]}"
        val filter = ListFilterTvShows.getAndFilter(listOf(
                ListFilterTvShows.getOrFilter(listOf(
                        ListFilterTvShows.getRuleFilter(
                                ListFilterRuleTvShows(ListFilterFieldsTVShows.ACTOR, ListFilterRule(ListFilterOperators.CONTAINS, listOf("A1"))))))))

        val postActual = "\"filter\":{${filter.toJSON()}}"
        Assert.assertEquals(postExpected, postActual)
    }
}
