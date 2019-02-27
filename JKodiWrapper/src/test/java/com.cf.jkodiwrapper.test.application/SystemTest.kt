package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.system.KodiSystem
import com.cf.jkodiwrapper.methods.system.respond.PropertiesRespond
import com.cf.jkodiwrapper.types.system.SystemPropertyName
import com.cf.jkodiwrapper.types.system.SystemPropertyValue
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
class SystemTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiSystem("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"System.EjectOpticalDrive\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.ejectOpticalDrive(KodiID(5))
        val expected = KodiStringRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testEjectOpticalDrive() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.EjectOpticalDrive\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.ejectOpticalDrive(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetProperties() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"canhibernate\":true,\"canreboot\":true,\"canshutdown\":true,\"cansuspend\":true}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.GetProperties\", \"params\": {\"properties\":[\"canshutdown\",\"cansuspend\",\"canhibernate\",\"canreboot\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProperties(KodiID(1), SystemPropertyName.getAllFields())
        val expected = PropertiesRespond(SystemPropertyValue(true, true, true, true))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testHibernate() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.Hibernate\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.hibernate(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testReboot() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.Reboot\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.reboot(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testShutdown() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.Shutdown\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.shutdown(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testSuspend() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"System.Suspend\", \"params\": {}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.suspend(KodiID(1))
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}