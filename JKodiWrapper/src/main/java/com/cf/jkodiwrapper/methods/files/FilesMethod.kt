package com.cf.jkodiwrapper.methods.files

import com.cf.jkodiwrapper.general.methods.KodiMethod

enum class FilesMethod(var methodName: String) : KodiMethod {

    GET_DIRECTORY("Files.GetDirectory"),
    GET_FILE_DETAILS("Files.GetFileDetails"),
    GET_SOURCES("Files.GetSources"),
    PREPARE_DOWNLOAD("Files.PrepareDownload"),
    SET_FILE_DETAILS("Files.SetFileDetails");

    override fun toString(): String {
        return methodName
    }
}