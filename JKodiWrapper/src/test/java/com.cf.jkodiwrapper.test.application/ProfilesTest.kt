package com.cf.jkodiwrapper.test.application

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.error.KodiError
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.profiles.KodiProfiles
import com.cf.jkodiwrapper.methods.profiles.respond.ProfileRespond
import com.cf.jkodiwrapper.methods.profiles.respond.ProfilesRespond
import com.cf.jkodiwrapper.methods.profiles.respond.entity.Profiles
import com.cf.jkodiwrapper.types.list.ListLimitsReturned
import com.cf.jkodiwrapper.types.profiles.ProfilesDetailsProfile
import com.cf.jkodiwrapper.types.profiles.ProfilesFieldsProfile
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
class ProfilesTest {

    private val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    private val postURL = "http://127.0.0.1:8080/jsonrpc"

    private val kodi = KodiProfiles("127.0.0.1", 8080)

    init {
        PowerMockito.mockStatic(RequestUtil::class.java)
    }

    @Test
    fun testError() {
        val error = "{\"error\":{\"code\":-32601,\"message\":\"Method not found.\"},\"id\":5,\"jsonrpc\":\"2.0\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"Profiles.GetCurrentProfile\", \"params\": {\"properties\":[\"thumbnail\",\"lockmode\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                error, arrayOf()))

        val actual = kodi.getCurrentProfile(KodiID(5), ProfilesFieldsProfile.getAllFields())
        val expected = ProfileRespond(null)
        expected.id = 5
        expected.error = KodiError(-32601, null, "Method not found.")
        assertEquals(expected, actual)
    }

    @Test
    fun testGetCurrentProfile() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"label\":\"Master user\",\"lockmode\":0,\"thumbnail\":\"\"}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Profiles.GetCurrentProfile\", \"params\": {\"properties\":[\"thumbnail\",\"lockmode\"]}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getCurrentProfile(KodiID(1), ProfilesFieldsProfile.getAllFields())
        val p = ProfilesDetailsProfile(0, "")
        p.label = "Master user"
        val expected = ProfileRespond(p)
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testGetProfiles() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":{\"limits\":{\"end\":2,\"start\":0,\"total\":2},\"profiles\":[{\"label\":\"Master user\",\"lockmode\":0,\"thumbnail\":\"\"},{\"label\":\"Test\",\"lockmode\":0,\"thumbnail\":\"image://path/to/thumb.jpg\"}]}}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Profiles.GetProfiles\", \"params\": {\"properties\":[\"thumbnail\",\"lockmode\"],\"limits\":{\"start\":0,\"end\":2000000},\"sort\":{\"ignorearticle\":false,\"method\":\"none\",\"order\":\"ascending\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.getProfiles(KodiID(1), ProfilesFieldsProfile.getAllFields())
        val p1 = ProfilesDetailsProfile(0, "")
        p1.label = "Master user"
        val p2 = ProfilesDetailsProfile(0, "image://path/to/thumb.jpg")
        p2.label = "Test"
        val expected = ProfilesRespond(Profiles(listOf(p1, p2), ListLimitsReturned(2, 0, 2)))
        expected.id = 1
        assertEquals(expected, actual)
    }

    @Test
    fun testLoadProfile() {
        val respond = "{\"id\":1,\"jsonrpc\":\"2.0\",\"result\":\"OK\"}"
        val post = "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"Profiles.LoadProfile\", \"params\": {\"profile\":\"Master user\",\"prompt\":false,\"password\":{\"encryption\":\"md5\",\"value\":\"\"}}}"
        PowerMockito.`when`(RequestUtil.sendPostRequest(postURL, post, header)).thenReturn(RequestRespond(
                respond, arrayOf()))

        val actual = kodi.loadProfile(KodiID(1), "Master user")
        val expected = KodiStringRespond("OK")
        expected.id = 1
        assertEquals(expected, actual)
    }
}