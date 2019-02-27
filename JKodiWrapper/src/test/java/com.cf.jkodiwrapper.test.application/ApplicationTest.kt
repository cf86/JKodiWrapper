package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiBoolRespond
import com.cf.jkodiwrapper.general.respond.KodiIntRespond
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.application.KodiApplication
import com.cf.jkodiwrapper.methods.application.respond.PropertiesRespond
import com.cf.jkodiwrapper.types.application.ApplicationPropertyValue
import com.cf.jkodiwrapper.types.global.GlobalIncrementDecrement
import com.cf.jkodiwrapper.util.RequestRespond
import com.cf.jkodiwrapper.util.RequestUtil
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(RequestUtil::class)
@PowerMockIgnore("javax.net.ssl.*")
class ApplicationTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiApplication("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Application.SetMute\", \"params\": {\"mute\":false}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.setMute(KodiID(5), false)
        val expected = KodiBoolRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testQuit() {
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Application.Quit\", \"params\": {}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}", arrayOf()))

        val actual = kodi.quit(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1

        assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"muted\":false,\"name\":\"Kodi\",\"version\":{\"major\":17,\"minor\":6,\"revision\":\"20171114-a9a7a20\",\"tag\":\"stable\"},\"volume\":100}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Application.GetProperties\", \"params\": {\"properties\":[\"muted\",\"name\",\"version\",\"volume\"]}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProperties(KodiID(1))
        val version = HashMap<String, String>()
        version["major"] = "17"
        version["minor"] = "6"
        version["revision"] = "20171114-a9a7a20"
        version["tag"] = "stable"
        val expected = PropertiesRespond(ApplicationPropertyValue(false, "Kodi", version, 100))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testMuteToggle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":true}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Application.SetMute\", \"params\": {\"mute\":\"toggle\"}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setMute(KodiID(1), null)
        val expected = KodiBoolRespond(true)
        expected.id = 1

        assertEquals(expected, actual)
    }

    @Test
    fun testMuteVal() {
        val respond = "{\"id\":2,\"jsonrpc\":\"2.0\",\"result\":false}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"Application.SetMute\", \"params\": {\"mute\":false}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setMute(KodiID(2), false)
        val expected = KodiBoolRespond(false)
        expected.id = 2

        assertEquals(expected, actual)
    }

    @Test
    fun testVolumeVal() {
        // set volume
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":20}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Application.SetVolume\", \"params\": {\"volume\":20}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setVolume(KodiID(1), 20)
        val expected = KodiIntRespond(20)
        expected.id = 1

        assertEquals(actual, expected)
    }

    @Test
    fun testVolumeIncrDecr() {
        // decrement
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":97} "
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Application.SetVolume\", \"params\": {\"volume\":\"decrement\"}}"
        `when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setVolume(KodiID(1), GlobalIncrementDecrement.DECREMENT)
        val expected = KodiIntRespond(97)
        expected.id = 1

        assertEquals(actual, expected)
    }
}