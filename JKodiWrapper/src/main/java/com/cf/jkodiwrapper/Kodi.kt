package com.cf.jkodiwrapper

import com.cf.jkodiwrapper.methods.addons.KodiAddons
import com.cf.jkodiwrapper.methods.application.KodiApplication
import com.cf.jkodiwrapper.methods.audio.KodiAudio
import com.cf.jkodiwrapper.methods.favourites.KodiFavourites
import com.cf.jkodiwrapper.methods.files.KodiFiles
import com.cf.jkodiwrapper.methods.gui.KodiGUI
import com.cf.jkodiwrapper.methods.input.KodiInput
import com.cf.jkodiwrapper.methods.player.KodiPlayer
import com.cf.jkodiwrapper.methods.playlist.KodiPlaylist
import com.cf.jkodiwrapper.methods.profiles.KodiProfiles
import com.cf.jkodiwrapper.methods.system.KodiSystem
import com.cf.jkodiwrapper.methods.video.KodiVideo

class Kodi @JvmOverloads constructor(var ip: String, var port: Int, var useHTTPS: Boolean = false) {

    val addons = KodiAddons(ip, port, useHTTPS)
    val application = KodiApplication(ip, port, useHTTPS)
    val audio = KodiAudio(ip, port, useHTTPS)
    val favourites = KodiFavourites(ip, port, useHTTPS)
    val files = KodiFiles(ip, port, useHTTPS)
    val gui = KodiGUI(ip, port, useHTTPS)
    val input = KodiInput(ip, port, useHTTPS)
    val player = KodiPlayer(ip, port, useHTTPS)
    val playlist = KodiPlaylist(ip, port, useHTTPS)
    val profiles = KodiProfiles(ip, port, useHTTPS)
    val system = KodiSystem(ip, port, useHTTPS)
    val video = KodiVideo(ip, port, useHTTPS)
}