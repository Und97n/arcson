package org.philips.arcson.type

open class ArcsonValueTypeNumber internal constructor(uid: TypeUID): ArcsonValueTypeSimple(uid) {
    companion object: ArcsonValueTypeNumber(TypeUID.ARCSON_NUMBER_TYPE_UID) {

    }
}