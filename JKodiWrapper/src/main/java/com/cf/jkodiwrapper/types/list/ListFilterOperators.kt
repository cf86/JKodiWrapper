package com.cf.jkodiwrapper.types.list

enum class ListFilterOperators(var field: String) {

    AFTER("after"),
    BEFORE("before"),
    BETWEEN("between"),
    CONTAINS("contains"),
    DOESNOTCONTAIN("doesnotcontain"),
    ENDSWITH("endswith"),
    FALSE("false"),
    GREATERTHAN("greaterthan"),
    INTHELAST("inthelast"),
    IS("is"),
    ISNOT("isnot"),
    LESSTHAN("lessthan"),
    NOTINTHELAST("notinthelast"),
    STARTSWITH("startswith"),
    TRUE("true");

    override fun toString(): String {
        return field
    }
}