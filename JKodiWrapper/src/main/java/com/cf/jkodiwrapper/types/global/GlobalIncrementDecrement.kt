package com.cf.jkodiwrapper.types.global

enum class GlobalIncrementDecrement(var param: String) {

    INCREMENT("increment"),
    DECREMENT("decrement");

    override fun toString(): String {
        return param
    }
}