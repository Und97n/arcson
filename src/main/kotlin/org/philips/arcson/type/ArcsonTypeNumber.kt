package org.philips.arcson.type

open class ArcsonTypeNumber internal constructor(id: TypeId): ArcsonTypeSimple(id) {
    companion object: ArcsonTypeNumber(TypeId.ARCSON_NUMBER_TYPE_ID) {

    }

    override fun toString(): String =
        "ArcsonTypeNumber"
}