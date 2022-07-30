package org.philips.arcson.type

open class ArcsonValueTypeObject private constructor(uid: TypeUID): ArcsonValueTypeComplex(uid) {
    companion object: ArcsonValueTypeObject(TypeUID.ARCSON_OBJECT_TYPE_UID) {

    }
}