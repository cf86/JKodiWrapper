package com.cf.jkodiwrapper.general.request.param.post

import com.cf.jkodiwrapper.general.request.param.post.property.KodiBoolProperty

data class KodiBoolParam(var key: String,
                         var bool: Boolean) : KodiPropertiesParam(listOf(KodiBoolProperty(key, bool)))