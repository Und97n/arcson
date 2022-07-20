package org.philips.arcson.type

open class ArcsonValueTypeObject private constructor(uid: ArcsonValueTypeUID): ArcsonValueTypeComplex(uid) {
    companion object: ArcsonValueTypeObject(ArcsonValueTypeUID.ARCSON_OBJECT_TYPE_UID) {

    }
}