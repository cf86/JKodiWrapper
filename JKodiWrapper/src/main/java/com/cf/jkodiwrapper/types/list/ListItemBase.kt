package com.cf.jkodiwrapper.types.list

import com.cf.jkodiwrapper.types.audio.AudioContributors
import com.cf.jkodiwrapper.types.video.VideoCast
import com.cf.jkodiwrapper.types.video.VideoDetailsFile
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

// since it can only inherit from 1 class, AudioDetailsMedia and AudioDetailsBase will be added here as parameters
@JsonIgnoreProperties(ignoreUnknown = true)
open class ListItemBase( // AudioDetailsBase
        // var dateadded: String? = null,
        var genre: List<String> = listOf(),
        // AudioDetailsMedia
        var artist: List<String> = listOf(),
        var artistid: List<Int> = listOf(),
        var displayartist: String? = null,
        var genreid: List<Int> = listOf(),
        var musicbrainzalbumartistid: List<String> = listOf(),
        var musicbrainzalbumid: String? = null,
        var rating: Double = 0.0,
        // var title: String? = null,
        var userrating: Int = 0,
        var votes: String = "0",
        var year: Int = 0,
        // ListItemBase Params
        var album: String? = null,
        var albumartist: List<String> = listOf(),
        var albumartistid: List<Int> = listOf(),
        var albumid: Int = -1,
        var albumlabel: String? = null,
        var albumreleasetype: String = "album",
        var cast: List<VideoCast> = listOf(),
        var comment: String? = null,
        var compilation: Boolean = false,
        var contributors: List<AudioContributors> = listOf(),
        var country: List<String> = listOf(),
        var description: String? = null,
        var disc: Int = 0,
        var displaycomposer: String? = null,
        var displayconductor: String? = null,
        var displaylyricist: String? = null,
        var displayorchestra: String? = null,
        var duration: Int = 0,
        var episode: Int = 0,
        var episodeguide: String? = null,
        var firstaired: String? = null,
        var id: Int = -1,
        var imdbnumber: String? = null,
        var lyrics: String? = null,
        var mood: List<String> = listOf(),
        var mpaa: String? = null,
        var musicbrainzartistid: List<String> = listOf(),
        var musicbrainztrackid: String? = null,
        var originaltitle: String? = null,
        var plotoutline: String? = null,
        var premiered: String? = null,
        var productioncode: String? = null,
        var releasetype: String = "album",
        var season: Int = 0,
        var set: String? = null,
        var setid: Int = -1,
        var showlink: List<String> = listOf(),
        var showtitle: String? = null,
        var sorttitle: String? = null,
        var specialsortepisode: Int = 0,
        var specialsortseason: Int = 0,
        var studio: List<String> = listOf(),
        var style: List<String> = listOf(),
        var tag: List<String> = listOf(),
        var tagline: String? = null,
        var theme: List<String> = listOf(),
        var top250: Int = 0,
        var track: Int = 0,
        var trailer: String? = null,
        var tvshowid: Int = -1,
        var type: String = "unknown",
        var uniqueid: Any? = null,
//                        var votes: String? = null,
        var watchedepisodes: Int = 0,
        var writer: List<String> = listOf()) : VideoDetailsFile() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? ListItemBase ?: return false
        return genre == obj.genre && artist == obj.artist && artistid == obj.artistid && displayartist == obj.displayartist && genreid == obj.genreid
                && musicbrainzalbumartistid == obj.musicbrainzalbumartistid && musicbrainzalbumid == obj.musicbrainzalbumid && rating == obj.rating
                && userrating == obj.userrating && votes == obj.votes && year == obj.year && album == obj.album && albumartist == obj.albumartist
                && albumartistid == obj.albumartistid && albumid == obj.albumid && albumlabel == obj.albumlabel && albumreleasetype == obj.albumreleasetype
                && cast == obj.cast && comment == obj.comment && compilation == obj.compilation && contributors == obj.contributors && country == obj.country
                && description == obj.description && disc == obj.disc && displaycomposer == obj.displaycomposer && displayconductor == obj.displayconductor
                && displaylyricist == obj.displaylyricist && displayorchestra == obj.displayorchestra && duration == obj.duration && episode == obj.episode
                && episodeguide == obj.episodeguide && firstaired == obj.firstaired && id == obj.id && imdbnumber == obj.imdbnumber && lyrics == obj.lyrics
                && mood == obj.mood && mpaa == obj.mpaa && musicbrainzartistid == obj.musicbrainzartistid && musicbrainztrackid == obj.musicbrainztrackid
                && originaltitle == obj.originaltitle && plotoutline == obj.plotoutline && premiered == obj.premiered && productioncode == obj.productioncode
                && releasetype == obj.releasetype && season == obj.season && set == obj.set && setid == obj.setid && showlink == obj.showlink
                && showtitle == obj.showtitle && sorttitle == obj.sorttitle && specialsortepisode == obj.specialsortepisode
                && specialsortseason == obj.specialsortseason && studio == obj.studio && style == obj.style && tag == obj.tag && tagline == obj.tagline
                && theme == obj.theme && top250 == obj.top250 && track == obj.track && trailer == obj.trailer && tvshowid == obj.tvshowid
                && type == obj.type && uniqueid == obj.uniqueid && watchedepisodes == obj.watchedepisodes && writer == obj.writer && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + artist.hashCode()
        result = 31 * result + artistid.hashCode()
        result = 31 * result + (displayartist?.hashCode() ?: 0)
        result = 31 * result + genreid.hashCode()
        result = 31 * result + (musicbrainzalbumartistid.hashCode())
        result = 31 * result + (musicbrainzalbumid?.hashCode() ?: 0)
        result = 31 * result + rating.hashCode()
        result = 31 * result + userrating
        result = 31 * result + votes.hashCode()
        result = 31 * result + year
        result = 31 * result + (album?.hashCode() ?: 0)
        result = 31 * result + albumartist.hashCode()
        result = 31 * result + albumartistid.hashCode()
        result = 31 * result + albumid
        result = 31 * result + (albumlabel?.hashCode() ?: 0)
        result = 31 * result + albumreleasetype.hashCode()
        result = 31 * result + cast.hashCode()
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + compilation.hashCode()
        result = 31 * result + (contributors.hashCode())
        result = 31 * result + country.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + disc
        result = 31 * result + (displaycomposer?.hashCode() ?: 0)
        result = 31 * result + (displayconductor?.hashCode() ?: 0)
        result = 31 * result + (displaylyricist?.hashCode() ?: 0)
        result = 31 * result + (displayorchestra?.hashCode() ?: 0)
        result = 31 * result + duration
        result = 31 * result + episode
        result = 31 * result + (episodeguide?.hashCode() ?: 0)
        result = 31 * result + (firstaired?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + (imdbnumber?.hashCode() ?: 0)
        result = 31 * result + (lyrics?.hashCode() ?: 0)
        result = 31 * result + mood.hashCode()
        result = 31 * result + (mpaa?.hashCode() ?: 0)
        result = 31 * result + (musicbrainzartistid.hashCode())
        result = 31 * result + (musicbrainztrackid?.hashCode() ?: 0)
        result = 31 * result + (originaltitle?.hashCode() ?: 0)
        result = 31 * result + (plotoutline?.hashCode() ?: 0)
        result = 31 * result + (premiered?.hashCode() ?: 0)
        result = 31 * result + (productioncode?.hashCode() ?: 0)
        result = 31 * result + releasetype.hashCode()
        result = 31 * result + season
        result = 31 * result + (set?.hashCode() ?: 0)
        result = 31 * result + setid
        result = 31 * result + showlink.hashCode()
        result = 31 * result + (showtitle?.hashCode() ?: 0)
        result = 31 * result + (sorttitle?.hashCode() ?: 0)
        result = 31 * result + specialsortepisode
        result = 31 * result + specialsortseason
        result = 31 * result + studio.hashCode()
        result = 31 * result + style.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (tagline?.hashCode() ?: 0)
        result = 31 * result + theme.hashCode()
        result = 31 * result + top250
        result = 31 * result + track
        result = 31 * result + (trailer?.hashCode() ?: 0)
        result = 31 * result + tvshowid
        result = 31 * result + type.hashCode()
        result = 31 * result + (uniqueid?.hashCode() ?: 0)
        result = 31 * result + watchedepisodes
        result = 31 * result + writer.hashCode()
        return result
    }
}