package org.philips.arcson.schema

fun<K, T> MutableMap<K, T>.getOrMake(key: K, createFn: () -> T): T =
    this[key] ?: createFn().let {
        this@getOrMake[key] = it
        it
    }