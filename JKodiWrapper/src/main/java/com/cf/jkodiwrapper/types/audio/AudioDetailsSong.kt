package com.cf.jkodiwrapper.types.audio

data class AudioDetailsSong(var album: String? = null,
                            var albumartist: List<String> = listOf(),
                            var albumartistid: List<Int> = listOf(),
                            var albumid: Int = -1,
                            var albumreleasetype: String = "album",
                            var comment: String? = null,
                            var contributors: List<AudioContributors> = listOf(),
                            var disc: Int = 0,
                            var displaycomposer: String? = null,
                            var displayconductor: String? = null,
                            var displaylyricist: String? = null,
                            var displayorchestra: String? = null,
                            var duration: Int = 0,
                            var file: String? = null,
                            var lastplayed: String? = null,
                            var lyrics: String? = null,
                            var mood: List<String> = listOf(),
                            var musicbrainzartistid: List<String> = listOf(),
                            var musicbrainztrackid: String? = null,
                            var playcount: Int = 0,
                            var songid: Int? = null,
                            var track: Int = 0) : AudioDetailsMedia() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AudioDetailsSong ?: return false
        return album == obj.album && albumartist == obj.albumartist && albumartistid == obj.albumartistid && albumid == obj.albumid &&
                albumreleasetype == obj.albumreleasetype && comment == obj.comment && contributors == obj.contributors &&
                disc == obj.disc && displaycomposer == obj.displaycomposer && displayconductor == obj.displayconductor &&
                displaylyricist == obj.displaylyricist && displayorchestra == obj.displayorchestra && duration == obj.duration &&
                file == obj.file && lastplayed == obj.lastplayed && lyrics == obj.lyrics && mood == obj.mood &&
                musicbrainzartistid == obj.musicbrainzartistid && musicbrainztrackid == obj.musicbrainztrackid && playcount == obj.playcount &&
                songid == obj.songid && track == obj.track && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (album?.hashCode() ?: 0)
        result = 31 * result + albumartist.hashCode()
        result = 31 * result + albumartistid.hashCode()
        result = 31 * result + albumid
        result = 31 * result + albumreleasetype.hashCode()
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + contributors.hashCode()
        result = 31 * result + disc
        result = 31 * result + (displaycomposer?.hashCode() ?: 0)
        result = 31 * result + (displayconductor?.hashCode() ?: 0)
        result = 31 * result + (displaylyricist?.hashCode() ?: 0)
        result = 31 * result + (displayorchestra?.hashCode() ?: 0)
        result = 31 * result + duration
        result = 31 * result + (file?.hashCode() ?: 0)
        result = 31 * result + (lastplayed?.hashCode() ?: 0)
        result = 31 * result + (lyrics?.hashCode() ?: 0)
        result = 31 * result + (mood.hashCode())
        result = 31 * result + (musicbrainzartistid.hashCode())
        result = 31 * result + (musicbrainztrackid?.hashCode() ?: 0)
        result = 31 * result + playcount
        result = 31 * result + (songid ?: 0)
        result = 31 * result + track
        return result
    }
}