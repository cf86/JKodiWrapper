package com.cf.jkodiwrapper.methods

import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.respond.KodiRespond
import com.cf.jkodiwrapper.util.RequestUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory

abstract class KodiModule(ip: String, port: Int, useHTTPS: Boolean = false) {

    val postURL = "${if (useHTTPS) "https://" else "http://"}$ip:$port/jsonrpc"
    val header: Map<String, String> = mapOf(Pair("Content-Type", "application/json"))
    val om = jacksonObjectMapper()

    inline fun <reified T : KodiRespond> makeCall(post: KodiPostData): T {
        LoggerFactory.getLogger(this.javaClass).info("POST general: ${post.toJsonString()}")
        try {
            val r = RequestUtil.sendPostRequest(postURL, post.toJsonString(), header)
                    ?: throw KodiException("Respond can't be null.")
            return om.readValue(r.response)
        } catch (e: Exception) {
            throw KodiException(if (e is KodiException) e.message ?: "" else "Couldn't call ${post.method}.", e)
        }
    }
}