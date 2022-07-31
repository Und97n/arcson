package org.philips.arcson.type

open class ArcsonTypeArray private constructor(id: TypeId): ArcsonTypeComplex(id) {
    companion object: ArcsonTypeArray(TypeId.ARCSON_ARRAY_TYPE_ID) {

    }

    override fun toString(): String =
        "ArcsonTypeArray"
}