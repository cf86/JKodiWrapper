package com.cf.jkodiwrapper.general.error

class KodiException : Exception {

    constructor(message: String, ex: Exception) : super(message, ex)
    constructor(message: String) : super(message)
    constructor(ex: Exception) : super(ex)
}