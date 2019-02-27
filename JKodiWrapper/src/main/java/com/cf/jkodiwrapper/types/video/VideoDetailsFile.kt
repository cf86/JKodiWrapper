package com.cf.jkodiwrapper.types.video

open class VideoDetailsFile(var director: List<String> = listOf(),
                            var resume: VideoResume? = null,
                            var runtime: Int = 0,
                            var streamdetails: VideoStreams? = null) : VideoDetailsItem() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? VideoDetailsFile ?: return false
        return director == obj.director && resume == obj.resume && runtime == obj.runtime && streamdetails == obj.streamdetails && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = director.hashCode()
        result = 31 * result + (resume?.hashCode() ?: 0)
        result = 31 * result + runtime
        result = 31 * result + (streamdetails?.hashCode() ?: 0)
        return result
    }
}