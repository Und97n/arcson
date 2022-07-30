package org.philips.arcson.type

open class ArcsonValueTypeArray private constructor(uid: TypeUID): ArcsonValueTypeComplex(uid) {
    companion object: ArcsonValueTypeArray(TypeUID.ARCSON_ARRAY_TYPE_UID) {

    }
}