package com.cf.jkodiwrapper.types.audio

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AudioDetailsArtist(var artist: String? = null,
                              var artistid: Int? = null,
                              var born: String? = null,
                              var compilationartist: Boolean = false,
                              var description: String? = null,
                              var died: String? = null,
                              var disbanded: String? = null,
                              var formed: String? = null,
                              var instrument: List<String> = listOf(),
                              var isalbumartist: Boolean = false,
                              var mood: List<String> = listOf(),
                              var musicbrainzartistid: List<String> = listOf(),
                              var roles: List<AudioArtistRoles> = listOf(),
                              var songgenres: List<AudioDetailsGenres> = listOf(),
                              var style: List<String> = listOf(),
                              var yearsactive: List<String> = listOf()) : AudioDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsArtist ?: return false
        return artist == obj.artist && artistid == obj.artistid && born == obj.born && compilationartist == obj.compilationartist &&
                description == obj.description && died == obj.died && disbanded == obj.disbanded && formed == obj.formed &&
                instrument == obj.instrument && isalbumartist == obj.isalbumartist && mood == obj.mood &&
                musicbrainzartistid == obj.musicbrainzartistid && roles == obj.roles && songgenres == obj.songgenres &&
                style == obj.style && yearsactive == obj.yearsactive && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + (artistid ?: 0)
        result = 31 * result + (born?.hashCode() ?: 0)
        result = 31 * result + compilationartist.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (died?.hashCode() ?: 0)
        result = 31 * result + (disbanded?.hashCode() ?: 0)
        result = 31 * result + (formed?.hashCode() ?: 0)
        result = 31 * result + instrument.hashCode()
        result = 31 * result + isalbumartist.hashCode()
        result = 31 * result + mood.hashCode()
        result = 31 * result + musicbrainzartistid.hashCode()
        result = 31 * result + roles.hashCode()
        result = 31 * result + songgenres.hashCode()
        result = 31 * result + style.hashCode()
        result = 31 * result + yearsactive.hashCode()
        return result
    }
}