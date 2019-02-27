package com.cf.jkodiwrapper.types.list

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ListFilterRuleSongs(var field: ListFilterFieldsSongs,
                               var rule: ListFilterRule) {

    fun toJSON(): String {
        return "\"field\":\"$field\",\"operator\":\"${rule.operator}\",\"value\":${if (rule.value.size == 1) "\"${rule.value[0]}\""
        else "[${rule.value.joinToString(",") { "\"$it\"" }}]"}"
    }
}