package org.philips.arcson.type

@JvmInline
value class ArcsonValueTypeUID private constructor(private val uid: Byte) {
    companion object {
        private fun Int.cast(): ArcsonValueTypeUID = ArcsonValueTypeUID(this.toByte())

        val ARCSON_UNKNOWN_TYPE_UID: ArcsonValueTypeUID = 0x00.cast()
        val ARCSON_OBJECT_TYPE_UID: ArcsonValueTypeUID = 0x01.cast()
        val ARCSON_ARRAY_TYPE_UID: ArcsonValueTypeUID = 0x02.cast()
        val ARCSON_NUMBER_TYPE_UID: ArcsonValueTypeUID = 0x03.cast()
        val ARCSON_STRING_TYPE_UID: ArcsonValueTypeUID = 0x04.cast()

        val MAX_VALUE: Int = 0x4
    }

    fun asIntValue(): Int = uid.toInt()
}
