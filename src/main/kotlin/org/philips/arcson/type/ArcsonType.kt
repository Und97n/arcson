package org.philips.arcson.type

abstract class ArcsonType internal constructor(val id: TypeId) {
    init {
        init()
    }

    protected open fun init() {
        id.init(this)

    }
}