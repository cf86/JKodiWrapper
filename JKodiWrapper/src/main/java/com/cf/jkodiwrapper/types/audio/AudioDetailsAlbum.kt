package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioDetailsAlbum(var albumid: Int? = null,
                             var albumlabel: String? = null,
                             var compilation: Boolean = false,
                             var description: String? = null,
                             var mood: List<String> = listOf(),
                             var playcount: Int = 0,
                             var releasetype: String = "album",
                             var style: List<String> = listOf(),
                             var theme: List<String> = listOf(),
                             var type: String? = null) : AudioDetailsMedia() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsAlbum ?: return false
        return albumid == obj.albumid && albumlabel == obj.albumlabel && compilation == obj.compilation && description == obj.description &&
                mood == obj.mood && playcount == obj.playcount && releasetype == obj.releasetype && style == obj.style &&
                theme == obj.theme && type == obj.type && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (albumid?.hashCode() ?: 0)
        result = 31 * result + (albumlabel?.hashCode() ?: 0)
        result = 31 * result + compilation.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + mood.hashCode()
        result = 31 * result + playcount
        result = 31 * result + releasetype.hashCode()
        result = 31 * result + style.hashCode()
        result = 31 * result + theme.hashCode()
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}