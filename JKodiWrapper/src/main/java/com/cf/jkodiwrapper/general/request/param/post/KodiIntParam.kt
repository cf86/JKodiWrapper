package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.param.post.property.KodiIntProperty

data class KodiIntParam(var key: String,
                        var num: Int) : KodiPropertiesParam(listOf(KodiIntProperty(key, num)))