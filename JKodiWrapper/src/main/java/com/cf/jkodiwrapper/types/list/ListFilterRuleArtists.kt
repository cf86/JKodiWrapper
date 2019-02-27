package com.cf.jkodiwrapper.types.list

data class ListFilterRuleArtists(var field: ListFilterFieldsArtists,
                                 var rule: ListFilterRule) {

    fun toJSON(): String {
        return "\"field\":\"$field\",\"operator\":\"${rule.operator}\",\"value\":${if (rule.value.size == 1) "\"${rule.value[0]}\""
        else "[${rule.value.joinToString(",") { "\"$it\"" }}]"}"
    }

}