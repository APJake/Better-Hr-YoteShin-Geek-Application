package com.apjake.yoteshingeek.common.base

interface UniMapper<A,B> {
    fun map(data: A): B
}