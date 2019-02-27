package com.cf.jkodiwrapper.types.addon

enum class AddonTypes(var type: String) {

    UNKNOWN("unknown"),
    KODI_ADSP("kodi.adsp"),
    KODI_AUDIODECODER("kodi.audiodecoder"),
    KODI_CONTEXT_ITEM("kodi.context.item"),
    KODI_GAME_CONTROLLER("kodi.game.controller"),
    KODI_INPUTSTREAM("kodi.inputstream"),
    KODI_PERIPHERAL("kodi.peripheral"),
    KODI_RESOURCE_IMAGES("kodi.resource.images"),
    KODI_RESOURCE_LANGUAGE("kodi.resource.language"),
    KODI_RESOURCE_UISOUNDS("kodi.resource.uisounds"),
    XBMC_ADDON_AUDIO("xbmc.addon.audio"),
    XBMC_ADDON_EXECUTABLE("xbmc.addon.executable"),
    XBMC_ADDON_IMAGE("xbmc.addon.image"),
    XBMC_ADDON_REPOSITORY("xbmc.addon.repository"),
    XBMC_ADDON_VIDEO("xbmc.addon.video"),
    XBMC_AUDIOENCODER("xbmc.audioencoder"),
    XBMC_GUI_SKIN("xbmc.gui.skin"),
    XBMC_METADATA_SCRAPER_ALBUMS("xbmc.metadata.scraper.albums"),
    XBMC_METADATA_SCRAPER_ARTISTS("xbmc.metadata.scraper.artists"),
    XBMC_METADATA_SCRAPER_LIBRARY("xbmc.metadata.scraper.library"),
    XBMC_METADATA_SCRAPER_MOVIES("xbmc.metadata.scraper.movies"),
    XBMC_METADATA_SCRAPER_MUSICVIDEOS("xbmc.metadata.scraper.musicvideos"),
    XBMC_METADATA_SCRAPER_TVSHOWS("xbmc.metadata.scraper.episodes"),
    XBMC_PLAYER_MUSICVIZ("xbmc.player.musicviz"),
    XBMC_PVRCLIENT("xbmc.pvrclient"),
    XBMC_PYTHON_LIBRARY("xbmc.python.library"),
    XBMC_PYTHON_LYRICS("xbmc.python.lyrics"),
    XBMC_PYTHON_MODULE("xbmc.python.module"),
    XBMC_PYTHON_PLUGINSOURCE("xbmc.python.pluginsource"),
    XBMC_PYTHON_SCRIPT("xbmc.python.script"),
    XBMC_PYTHON_WEATHER("xbmc.python.weather"),
    XBMC_SERVICE("xbmc.service"),
    XBMC_SUBTITLE_MODULE("xbmc.subtitle.module"),
    XBMC_UI_SCREENSAVER("xbmc.ui.screensaver"),
    XBMC_WEBINTERFACE("xbmc.webinterface");

    companion object {
        @JvmStatic
        fun generateAddonType(str: String): AddonTypes {
            return AddonTypes.values().find { it.type == str } ?: UNKNOWN
        }
    }
}