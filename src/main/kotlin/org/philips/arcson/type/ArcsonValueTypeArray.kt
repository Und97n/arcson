package org.philips.arcson.type

open class ArcsonValueTypeArray private constructor(uid: ArcsonValueTypeUID): ArcsonValueTypeComplex(uid) {
    companion object: ArcsonValueTypeArray(ArcsonValueTypeUID.ARCSON_ARRAY_TYPE_UID) {

    }
}