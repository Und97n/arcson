package org.philips.arcson.type

open class ArcsonTypeObject private constructor(id: TypeId): ArcsonTypeComplex(id) {
    companion object: ArcsonTypeObject(TypeId.ARCSON_OBJECT_TYPE_ID) {

    }
}