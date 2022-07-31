package org.philips.arcson.type

inline fun Int.asTypeId(): TypeId =
    TypeId(this.toByte())

inline fun Byte.asTypeId(): TypeId =
    TypeId(this)

@JvmInline
value class TypeId(private val uid: Byte) {
    companion object {
        private val uidToTypeMap = HashMap<TypeId, ArcsonType>()
        private fun Int.cast(): TypeId = TypeId(this.toByte())

        val ARCSON_NONE_TYPE_ID: TypeId = 0x00.cast()
        val ARCSON_OBJECT_TYPE_ID: TypeId = 0x01.cast()
        val ARCSON_ARRAY_TYPE_ID: TypeId = 0x02.cast()
        val ARCSON_NUMBER_TYPE_ID: TypeId = 0x03.cast()
        val ARCSON_STRING_TYPE_ID: TypeId = 0x04.cast()

        val MAX_VALUE: Int = 0x4
    }

    internal fun init(type: ArcsonType) {
        uidToTypeMap[this] = type
    }

    fun getType(): ArcsonType =
        uidToTypeMap[this] ?: ArcsonSpecialTypeUnknown(this)

    fun asIntValue(): Int = uid.toInt()

    override fun toString(): String =
        Integer.toHexString(uid.toInt())
}
