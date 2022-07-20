package org.philips.arcson.type

open class ArcsonValueTypeNumber internal constructor(uid: ArcsonValueTypeUID): ArcsonValueTypeSimple(uid) {
    companion object: ArcsonValueTypeNumber(ArcsonValueTypeUID.ARCSON_NUMBER_TYPE_UID) {

    }
}