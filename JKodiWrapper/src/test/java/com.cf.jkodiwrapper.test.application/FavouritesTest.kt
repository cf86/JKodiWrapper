package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.favourites.KodiFavourites
import com.cf.jkodiwrapper.methods.favourites.respond.FavouritesRespond
import com.cf.jkodiwrapper.methods.favourites.respond.entity.Favourites
import com.cf.jkodiwrapper.types.favourite.FavouriteDetailsFavourite
import com.cf.jkodiwrapper.types.favourite.FavouriteType
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
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
class FavouritesTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiFavourites("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Favourites.GetFavourites\", \"params\": {\"type\":null,\"properties\":[]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.getFavourites(KodiID(5))
        val expected = FavouritesRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testGetFavourites() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"favourites\":[{\"title\":\"test.mkv\",\"type\":\"media\"}],\"limits\":{\"end\":1,\"start\":0,\"total\":1}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Favourites.GetFavourites\", \"params\": {\"type\":null,\"properties\":[]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getFavourites(KodiID(1))
        val expected = FavouritesRespond(Favourites(listOf(FavouriteDetailsFavourite(null, null, "test.mkv", "media", null, null)), ListLimitsReturned(1, 0, 1)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testAddFavourite() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Favourites.AddFavourite\", \"params\": {\"title\":\"My Fav\",\"type\":\"media\",\"path\":\"/path/to/file.mkv\",\"thumbnail\":\"path/to/thumb\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.addFavourite(KodiID(1), "My Fav", FavouriteType.MEDIA, "/path/to/file.mkv", null, null, "path/to/thumb")
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}