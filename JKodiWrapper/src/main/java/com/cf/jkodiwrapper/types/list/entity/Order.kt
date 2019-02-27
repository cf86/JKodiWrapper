package com.cf.jkodiwrapper.types.list.entity

enum class Order(var order: String) {

    ASCENDING("ascending"),
    DESCENDING("ascending");

    override fun toString(): String {
        return order
    }
}