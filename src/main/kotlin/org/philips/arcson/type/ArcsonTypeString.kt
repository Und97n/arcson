package org.philips.arcson.type

open class ArcsonTypeString internal constructor(id: TypeId): ArcsonTypeSimple(id) {
    companion object: ArcsonTypeString(TypeId.ARCSON_STRING_TYPE_ID) {

    }
}