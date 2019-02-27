package com.cf.jkodiwrapper.util

import org.apache.commons.io.FilenameUtils
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException

object RequestUtil {

    @JvmField
    var LOG_LENGTH = 120

    /**
     * sends a POST Request
     *
     * @param url to the url
     * @param postData with the postdata
     * @param headerParams using the given header params
     * @param keepAlive keep alive flag
     * @param timeout the timeout in secs
     *
     * @return the respond
     *
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Throws(IOException::class, KeyStoreException::class, NoSuchAlgorithmException::class, KeyManagementException::class)
    @JvmStatic
    @JvmOverloads
    fun sendPostRequest(url: String, postData: String, headerParams: Map<String, String> = mapOf(), keepAlive: Boolean = true, timeout: Int = 60): RequestRespond? {
        val logger = LoggerFactory.getLogger(RequestUtil::class.java)

        val encURL = encodeURL(url)
        val httpClient = createHttpClient()
        val httpPost = HttpPost(encURL)
        if (!headerParams.isEmpty()) {
            for ((key, value) in headerParams) {
                httpPost.addHeader(key, value)
            }
        }
        if (keepAlive)
            httpPost.addHeader("Connection", "Keep-Alive")

        val sContent = StringEntity(postData, StandardCharsets.UTF_8)
        httpPost.entity = sContent

        logger.info("Call: {} with POST: {}", encURL, StringUtils.abbreviate(postData, LOG_LENGTH))
        return sendRequest(httpClient, httpPost, timeout)
    }

    /**
     * Send the request using the given client and the given request
     *
     * @param client the given client
     * @param r the given request
     * @param timeout the timeout in secs
     *
     * @return the respond
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    @JvmStatic
    private fun sendRequest(client: HttpClient, r: HttpRequestBase, timeout: Int): RequestRespond? {
        val logger = LoggerFactory.getLogger(RequestUtil::class.java)

        val resp = Unit<HttpResponse>()
        val cmd = {
            var res: HttpResponse? = null
            try {
                res = client.execute(r)
            } catch (e: IOException) {
                logger.error("Error while sending request.", e)
                resp.value = null
            }

            resp.value = res
        }

        val response: HttpResponse?
        // run command, if not successful
        response = if (!runCommand(cmd, timeout)) {
            null
        } else {
            resp.value
        }

        if (response == null) {
            logger.warn("Could not get response in time.")
            return null
        }

        var result: String? = null
        if (response.entity != null)
            result = IOUtils.toString(response.entity.content, StandardCharsets.UTF_8)

        logger.info("Received Respond: {}", StringUtils.abbreviate(result, LOG_LENGTH))
        return RequestRespond(result!!, response.allHeaders)
    }

    /**
     * Get an HTTP Client and deactivate Certificate Verification
     *
     * @return the HTTP Client
     *
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
    @JvmStatic
    private fun createHttpClient(): HttpClient {
        val builder = SSLContextBuilder()
        builder.loadTrustMaterial(null) { _, _ -> true }
        val sslsf = SSLConnectionSocketFactory(builder.build())

        return HttpClients.custom().setSSLSocketFactory(sslsf).disableRedirectHandling().build()
    }

    /**
     * Runs a command and stops the thread after the timeout time is reached
     *
     * @param cmd the command to run
     * @param timeoutSecs the timeout in secs
     *
     * @return true if finished before timeout, false if timeout occurred
     */
    @JvmStatic
    private fun runCommand(cmd: () -> Any, timeoutSecs: Int = 60): Boolean {
        val thread = Thread { cmd() }
        thread.start()

        val endTimeMillis = System.currentTimeMillis() + timeoutSecs * 1000
        while (thread.isAlive) {
            if (System.currentTimeMillis() > endTimeMillis) {
                thread.interrupt()
                return false
            }
            try {
                Thread.sleep(500)
            } catch (t: InterruptedException) {
                return false
            }

        }

        return true
    }

    private fun encodeURL(url: String): String {
        val u = URL(url)
        val path = URLEncoder.encode(separatorStartNoCareEnd(u.path), "UTF-8").replace("%2F", "/")
        val port = if (u.port == -1) "" else ":${u.port}"
        return "${u.protocol}://${u.host}$port$path"
    }

    @JvmStatic
    private fun separatorStartNoCareEnd(path: String): String {
        var result = makeUnixStyle(path)

        if (!result.startsWith("/"))
            result = "/$result"

        return result
    }

    @JvmStatic
    private fun makeUnixStyle(path: String): String {
        return FilenameUtils.separatorsToUnix(path)
    }
}