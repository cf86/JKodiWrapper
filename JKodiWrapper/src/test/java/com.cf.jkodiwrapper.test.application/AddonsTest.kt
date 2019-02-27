package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.addons.KodiAddons
import com.cf.jkodiwrapper.methods.addons.respond.AddonDetailsRespond
import com.cf.jkodiwrapper.methods.addons.respond.AddonsRespond
import com.cf.jkodiwrapper.methods.addons.respond.entity.Addon
import com.cf.jkodiwrapper.methods.addons.respond.entity.Addons
import com.cf.jkodiwrapper.types.addon.*
import com.cf.jkodiwrapper.types.addon.entity.AddonDependency
import com.cf.jkodiwrapper.types.list.ListLimits
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
class AddonsTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiAddons("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Addons.GetAddons\", \"params\": {\"type\":\"unknown\",\"content\":\"unknown\",\"enabled\":true,\"properties\":[\"author\",\"broken\",\"dependencies\",\"description\",\"disclaimer\",\"enabled\",\"extrainfo\",\"fanart\",\"installed\",\"name\",\"path\",\"rating\",\"summary\",\"thumbnail\",\"version\"],\"limits\":{\"start\":0,\"end\":2000000},\"installed\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.getAddons(KodiID(5), AddonTypes.UNKNOWN, AddonContent.UNKNOWN, true, AddonFields.getAllAddonFields(),
                ListLimits(), true)
        val expected = AddonsRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testGetAddons() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"addons\":[{\"addonid\":\"audiodecoder.modplug\",\"author\":\"spiff\",\"broken\":false,\"dependencies\":[{\"addonid\":\"kodi.audiodecoder\",\"optional\":false,\"version\":\"1.0.0\"}],\"description\":\"Modplug Audio Decoder\",\"disclaimer\":\"\",\"enabled\":false,\"extrainfo\":[],\"fanart\":\"\",\"installed\":true,\"name\":\"Modplug Audio Decoder\",\"path\":\"/path/to/audiodecoder.modplug\",\"rating\":-1,\"summary\":\"Modplug Audio Decoder\",\"thumbnail\":\"\",\"type\":\"kodi.audiodecoder\",\"version\":\"1.1.0\"},{\"addonid\":\"audiodecoder.nosefart\",\"author\":\"spiff\",\"broken\":false,\"dependencies\":[{\"addonid\":\"kodi.audiodecoder\",\"optional\":false,\"version\":\"1.0.0\"}],\"description\":\"Nosefart (NSF) Audio Decoder\",\"disclaimer\":\"\",\"enabled\":false,\"extrainfo\":[],\"fanart\":\"\",\"installed\":true,\"name\":\"Nosefart Audio Decoder\",\"path\":\"/path/to/audiodecoder.nosefart\",\"rating\":-1,\"summary\":\"Nosefart (NSF) Audio Decoder\",\"thumbnail\":\"\",\"type\":\"kodi.audiodecoder\",\"version\":\"1.1.0\"}],\"limits\":{\"end\":2,\"start\":0,\"total\":2}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.GetAddons\", \"params\": {\"type\":\"unknown\",\"content\":\"unknown\",\"enabled\":true,\"properties\":[\"author\",\"broken\",\"dependencies\",\"description\",\"disclaimer\",\"enabled\",\"extrainfo\",\"fanart\",\"installed\",\"name\",\"path\",\"rating\",\"summary\",\"thumbnail\",\"version\"],\"limits\":{\"start\":0,\"end\":2000000},\"installed\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getAddons(KodiID(1), AddonTypes.UNKNOWN, AddonContent.UNKNOWN, true, AddonFields.getAllAddonFields(),
                ListLimits(), true)
        val a1 = AddonDetails("audiodecoder.modplug", "spiff", false, listOf(AddonDependency("kodi.audiodecoder", false, "1.0.0")),
                "Modplug Audio Decoder", "", false, ArrayList(), "", true, "Modplug Audio Decoder", "/path/to/audiodecoder.modplug",
                -1, "Modplug Audio Decoder", "", "kodi.audiodecoder", "1.1.0")
        val a2 = AddonDetails("audiodecoder.nosefart", "spiff", false, listOf(AddonDependency("kodi.audiodecoder", false, "1.0.0")),
                "Nosefart (NSF) Audio Decoder", "", false, ArrayList(), "", true, "Nosefart Audio Decoder", "/path/to/audiodecoder.nosefart",
                -1, "Nosefart (NSF) Audio Decoder", "", "kodi.audiodecoder", "1.1.0")

        val expected = AddonsRespond(Addons(listOf(a1, a2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetAddonDetails() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"addon\":{\"addonid\":\"audiodecoder.modplug\",\"author\":\"spiff\",\"broken\":false,\"dependencies\":[{\"addonid\":\"kodi.audiodecoder\",\"optional\":false,\"version\":\"1.0.0\"}],\"description\":\"Modplug Audio Decoder\",\"disclaimer\":\"\",\"enabled\":false,\"extrainfo\":[],\"fanart\":\"\",\"installed\":true,\"name\":\"Modplug Audio Decoder\",\"path\":\"/path/to/audiodecoder.modplug\",\"rating\":-1,\"summary\":\"Modplug Audio Decoder\",\"thumbnail\":\"\",\"type\":\"kodi.audiodecoder\",\"version\":\"1.1.0\"}}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.GetAddonDetails\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"properties\":[\"author\",\"broken\",\"dependencies\",\"description\",\"disclaimer\",\"enabled\",\"extrainfo\",\"fanart\",\"installed\",\"name\",\"path\",\"rating\",\"summary\",\"thumbnail\",\"version\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getAddonDetails(KodiID(1), AddonID("audiodecoder.modplug"), AddonFields.getAllAddonFields())
        val a1 = AddonDetails("audiodecoder.modplug", "spiff", false, listOf(AddonDependency("kodi.audiodecoder", false, "1.0.0")),
                "Modplug Audio Decoder", "", false, ArrayList(), "", true, "Modplug Audio Decoder", "/path/to/audiodecoder.modplug",
                -1, "Modplug Audio Decoder", "", "kodi.audiodecoder", "1.1.0")
        val expected = AddonDetailsRespond(Addon(a1))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetAddonEnabledValue() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.SetAddonEnabled\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"enabled\":true}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setAddonEnabled(KodiID(1), AddonID("audiodecoder.modplug"), true)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSetAddonEnabledToggle() {
        // everything fine - toggle
        val respond = "{\"id\":2,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"Addons.SetAddonEnabled\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"enabled\":\"toggle\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.setAddonEnabled(KodiID(2), AddonID("audiodecoder.modplug"), null)
        val expected = KodiStringRespond("OK")
        expected.id = 2
        assertEquals(expected, actual)
    }

    @Test
    fun testExecuteAddonURL() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.ExecuteAddon\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"wait\":false,\"params\":\"?URL\"}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.executeAddon(KodiID(1), AddonID("audiodecoder.modplug"), KodiPath("?URL"), false)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testExecuteAddonItems() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.ExecuteAddon\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"wait\":false,\"params\":[\"i1\",\"i2\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.executeAddon(KodiID(1), AddonID("audiodecoder.modplug"), listOf("i1", "i2"), false)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testExecuteAddonMap() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Addons.ExecuteAddon\", \"params\": {\"addonid\":\"audiodecoder.modplug\",\"wait\":false,\"params\":{\"k1\":\"v1\",\"k2\":\"v2\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.executeAddon(KodiID(1), AddonID("audiodecoder.modplug"), mapOf(Pair("k1", "v1"), Pair("k2", "v2")), false)
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}