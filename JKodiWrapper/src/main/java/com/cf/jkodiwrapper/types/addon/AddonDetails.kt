package com.cf.jkodiwrapper.types.addon

import com.cf.jkodiwrapper.types.addon.entity.AddonDependency
import com.cf.jkodiwrapper.types.addon.entity.AddonExtraInfo
import com.cf.jkodiwrapper.types.item.ItemDetailsBase
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddonDetails(var addonid: String? = null,
                        var author: String? = null,
                        var broken: Boolean? = null,
                        var dependencies: List<AddonDependency> = listOf(),
                        var description: String? = null,
                        var disclaimer: String? = null,
                        var enabled: Boolean = false,
                        var extrainfo: List<AddonExtraInfo> = listOf(),
                        var fanart: String? = null,
                        var installed: Boolean = false,
                        var name: String? = null,
                        var path: String? = null,
                        var rating: Int = 0,
                        var summary: String? = null,
                        var thumbnail: String? = null,
                        var type: String? = null,
                        var version: String? = null) : ItemDetailsBase() {

    override fun equals(other: Any?): Boolean {
        val obj = other as? AddonDetails ?: return false
        return addonid == obj.addonid && author == obj.author && broken == obj.broken && dependencies == obj.dependencies
                && description == obj.description && disclaimer == obj.disclaimer && enabled == obj.enabled && extrainfo == obj.extrainfo
                && fanart == obj.fanart && installed == obj.installed && name == obj.name && path == obj.path && rating == obj.rating
                && summary == obj.summary && thumbnail == obj.thumbnail && type == obj.type && version == obj.version && super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (addonid?.hashCode() ?: 0)
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (broken?.hashCode() ?: 0)
        result = 31 * result + dependencies.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (disclaimer?.hashCode() ?: 0)
        result = 31 * result + enabled.hashCode()
        result = 31 * result + extrainfo.hashCode()
        result = 31 * result + (fanart?.hashCode() ?: 0)
        result = 31 * result + installed.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (path?.hashCode() ?: 0)
        result = 31 * result + rating
        result = 31 * result + (summary?.hashCode() ?: 0)
        result = 31 * result + (thumbnail?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (version?.hashCode() ?: 0)
        return result
    }
}