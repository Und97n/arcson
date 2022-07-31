package org.philips.arcson.type

open class ArcsonTypeSimple internal constructor(id: TypeId): ArcsonType(id) {

    override fun toString(): String =
        "ArcsonTypeSimple"
}