package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiBoolRespond
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.gui.KodiGUI
import com.cf.jkodiwrapper.methods.gui.respond.PropertiesRespond
import com.cf.jkodiwrapper.methods.gui.respond.StereoscopicModesRespond
import com.cf.jkodiwrapper.methods.gui.respond.entity.StereoscopicModes
import com.cf.jkodiwrapper.types.gui.*
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
class GUITest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiGUI("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"GUI.ActivateWindow\", \"params\": {\"window\":\"home\",\"parameters\":[\"home\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.activateWindow(KodiID(5), GUIWindow.HOME, listOf("home"))
        val expected = KodiStringRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testActivateWindow() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"GUI.ActivateWindow\", \"params\": {\"window\":\"home\",\"parameters\":[\"home\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.activateWindow(KodiID(5), GUIWindow.HOME, listOf("home"))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"currentcontrol\":{\"label\":\"Movies\"},\"currentwindow\":{\"id\":10000,\"label\":\"Home\"},\"fullscreen\":false,\"skin\":{\"id\":\"skin.estuary\",\"name\":\"Estuary\"},\"stereoscopicmode\":{\"label\":\"Disabled\",\"mode\":\"off\"}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.GetProperties\", \"params\": {\"properties\":[\"currentcontrol\",\"currentwindow\",\"fullscreen\",\"skin\",\"stereoscopicmode\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProperties(KodiID(1), GUIPropertyName.getAllFields())
        val expected = PropertiesRespond(GUIPropertyValue(mapOf(Pair("label", "Movies")), mapOf(Pair("id", 10000), Pair("label", "Home")), false,
                mapOf(Pair("id", "skin.estuary"), Pair("name", "Estuary")), GUIStereoscopyMode("Disabled", "off")))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetStereoscopicModes() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"stereoscopicmodes\":[{\"label\":\"Disabled\",\"mode\":\"off\"},{\"label\":\"Over / Under\",\"mode\":\"split_horizontal\"},{\"label\":\"Side by side\",\"mode\":\"split_vertical\"},{\"label\":\"Anaglyph red / cyan\",\"mode\":\"anaglyph_cyan_red\"}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.GetStereoscopicModes\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getStereoscopicModes(KodiID(1))
        val expected = StereoscopicModesRespond(StereoscopicModes(listOf(GUIStereoscopyMode("Disabled", "off"), GUIStereoscopyMode("Over / Under", "split_horizontal"),
                GUIStereoscopyMode("Side by side", "split_vertical"), GUIStereoscopyMode("Anaglyph red / cyan", "anaglyph_cyan_red"))))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testFullscreenValue() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":false}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.SetFullscreen\", \"params\": {\"fullscreen\":false}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setFullscreen(KodiID(1), false)
        val expected = KodiBoolRespond(false)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testFullscreenToggle() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":false}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.SetFullscreen\", \"params\": {\"fullscreen\":\"toggle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setFullscreen(KodiID(1))
        val expected = KodiBoolRespond(false)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetStereoscopicMode() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.SetStereoscopicMode\", \"params\": {\"mode\":\"off\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setStereoscopicMode(KodiID(1), "off")
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testShowNotification() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"GUI.ShowNotification\", \"params\": {\"title\":\"title\",\"message\":\"message\",\"image\":\"info\",\"displaytime\":5000}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.showNotification(KodiID(1), "title", "message", GUINotificationImage.INFO)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}