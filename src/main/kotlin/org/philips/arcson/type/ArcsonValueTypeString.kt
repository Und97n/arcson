package org.philips.arcson.type

open class ArcsonValueTypeString internal constructor(uid: ArcsonValueTypeUID): ArcsonValueTypeSimple(uid) {
    companion object: ArcsonValueTypeString(ArcsonValueTypeUID.ARCSON_STRING_TYPE_UID) {

    }
}