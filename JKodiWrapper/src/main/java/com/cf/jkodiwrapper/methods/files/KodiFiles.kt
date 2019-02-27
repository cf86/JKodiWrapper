package com.cf.jkodiwrapper.methods.files

import com.cf.jkodiwrapper.general.attributes.KodiID
import com.cf.jkodiwrapper.general.attributes.KodiPath
import com.cf.jkodiwrapper.general.error.KodiException
import com.cf.jkodiwrapper.general.request.KodiPostData
import com.cf.jkodiwrapper.general.request.param.post.KodiStringParam
import com.cf.jkodiwrapper.general.respond.KodiStringRespond
import com.cf.jkodiwrapper.methods.KodiModule
import com.cf.jkodiwrapper.methods.files.params.post.DirectoryParam
import com.cf.jkodiwrapper.methods.files.params.post.GetFileDetailParam
import com.cf.jkodiwrapper.methods.files.params.post.SetFileDetailsParam
import com.cf.jkodiwrapper.methods.files.params.post.SourceParam
import com.cf.jkodiwrapper.methods.files.respond.DirectoryRespond
import com.cf.jkodiwrapper.methods.files.respond.FileDetailsRespond
import com.cf.jkodiwrapper.methods.files.respond.PrepareDownloadRespond
import com.cf.jkodiwrapper.methods.files.respond.SourcesRespond
import com.cf.jkodiwrapper.types.files.FilesMedia
import com.cf.jkodiwrapper.types.list.ListFieldsFiles
import com.cf.jkodiwrapper.types.list.ListLimits
import com.cf.jkodiwrapper.types.list.ListSort
import com.cf.jkodiwrapper.types.video.VideoResume
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class KodiFiles @JvmOverloads constructor(ip: String, port: Int, useHTTPS: Boolean = false) : KodiModule(ip, port, useHTTPS) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Get the directories and files in the given directory
     *
     * @param id the ID which should be used for the call
     * @param directory the given path. e.g. /home/user/path/to/dir. For windows use e.g. C:\\\\path\\\\to\\\\file (escaped \\) The path needs to be registered in Kodi
     * @param mediaType the media type which should be returned. Other directories will be returned for any Media Type
     * @param properties the properties which should be returned. Use ListFieldFiles.getPropertiesFor(...) to get a predefined list for each Media Type
     * @param sort defines the sort of the returned files
     * @param limits defines how many files should be returned. Limit will be applied after the call, so a lower number will not increase the performance
     *
     * @return a list with all files in the given path
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getDirectories(id: KodiID, directory: KodiPath, mediaType: FilesMedia = FilesMedia.FILES, properties: List<ListFieldsFiles> = listOf(), sort: ListSort = ListSort(),
                       limits: ListLimits = ListLimits()): DirectoryRespond {
        logger.info("Get Directories for: $directory. ID: $id")
        val post = KodiPostData(id, FilesMethod.GET_DIRECTORY, DirectoryParam(directory, mediaType, properties, sort, limits))
        return makeCall(post)
    }

    /**
     * Get details for a specific file
     *
     * @param id the ID which should be used for the call
     * @param file the given path. e.g. /home/user/path/to/dir. For windows use e.g. C:\\\\path\\\\to\\\\file (escaped \\) The path needs to be registered in Kodi
     * @param mediaType the media type which should be returned.
     * @param properties the properties which should be returned. Use ListFieldFiles.getAllFields() to get all fields
     *
     * @return returns details about the specified file depending on the chosen Media Type
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getFileDetails(id: KodiID, file: KodiPath, mediaType: FilesMedia = FilesMedia.FILES, properties: List<ListFieldsFiles> = listOf()): FileDetailsRespond {
        logger.info("Get File Details for: $file . ID: $id")
        val post = KodiPostData(id, FilesMethod.GET_FILE_DETAILS, GetFileDetailParam(file, mediaType, properties))
        return makeCall(post)
    }

    /**
     * Get the sources of the media windows e.g. if a movie is running in Kodi it will return all it's media, e.g. if it is available in upnp and local hdd, it will return both sources
     *
     * @param id the ID which should be used for the call
     * @param mediaType the media type which should be returned.
     * @param sort defines the sort of the returned sources
     * @param limits defines how many sources should be returned. Limit will be applied after the call, so a lower number will not increase the performance
     *
     * @return returns all sources of the currently playing media
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun getSources(id: KodiID, mediaType: FilesMedia = FilesMedia.FILES, sort: ListSort = ListSort(), limits: ListLimits = ListLimits()): SourcesRespond {
        logger.info("Get Sources for $mediaType. ID: $id")
        val post = KodiPostData(id, FilesMethod.GET_SOURCES, SourceParam(mediaType, sort, limits))
        return makeCall(post)
    }

    /**
     * Provides a way to download a given file (e.g. providing an URL to the real file location)
     *
     * @param id the ID which should be used for the call
     * @param file the given path to the file which should be prepared. e.g. /home/user/path/to/dir. For windows use e.g. C:\\\\path\\\\to\\\\file (escaped \\) The path needs to be registered in Kodi
     *
     * @return returns a way to download the specified file e.g. over http
     */
    @Throws(KodiException::class)
    fun prepareDownload(id: KodiID, file: KodiPath): PrepareDownloadRespond {
        logger.info("Prepare Download for $file. ID: $id")
        val post = KodiPostData(id, FilesMethod.PREPARE_DOWNLOAD, KodiStringParam("path", file.path))
        return makeCall(post)
    }

    /**
     * Update the given specific file with the given details
     *
     * @param id the ID which should be used for the call
     * @param file the given path to the file which should be prepared. e.g. /home/user/path/to/dir. For windows use e.g. C:\\\\path\\\\to\\\\file (escaped \\) The path needs to be registered in Kodi
     * @param mediaType the media type which should be set. Currently only VIDEO is supported.
     * @param playcount the playcount which should be set. If lastplayed is set and playcount is null, it will be automatically set to 1
     * @param lastplayed the time it was played last.
     * @param resume sets the resume stayed, at what position the video should start when continue watching
     *
     * @return OK if everything went fine, else error
     */
    @JvmOverloads
    @Throws(KodiException::class)
    fun setFileDetails(id: KodiID, file: KodiPath, mediaType: FilesMedia = FilesMedia.VIDEO, playcount: Int? = null, lastplayed: LocalDateTime? = null,
                       resume: VideoResume = VideoResume()): KodiStringRespond {
        logger.info("Set File Details for $file. ID: $id")
        val post = KodiPostData(id, FilesMethod.SET_FILE_DETAILS, SetFileDetailsParam(file, mediaType, playcount, lastplayed?.format(DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss")), resume))
        return makeCall(post)
    }
}