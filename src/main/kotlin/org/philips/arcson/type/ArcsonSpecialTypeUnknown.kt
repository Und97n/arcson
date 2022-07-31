package org.philips.arcson.type

class ArcsonSpecialTypeUnknown(_id: TypeId): ArcsonType(_id) {
    override fun init() {
        // Do nothing
    }

    override fun toString(): String =
        "ArcsonTypeUnknown($id)"
}
