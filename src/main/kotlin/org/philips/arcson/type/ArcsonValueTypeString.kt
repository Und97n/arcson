package org.philips.arcson.type

open class ArcsonValueTypeString internal constructor(uid: TypeUID): ArcsonValueTypeSimple(uid) {
    companion object: ArcsonValueTypeString(TypeUID.ARCSON_STRING_TYPE_UID) {

    }
}