@file:Suppress("NOTHING_TO_INLINE", "unused")

package org.philips.arcson.bcoder.common

import org.philips.arcson.bcoder.utils.BCoderException

inline fun Byte.wrapToByteWrapper(): ByteWrapper =
    ByteWrapper(this.toInt() and 0xFF)

inline fun Short.wrapToByteWrapper(): ByteWrapper =
    ByteWrapper(this.toInt() and 0xFF)

inline fun Int.wrapToByteWrapper(): ByteWrapper =
    ByteWrapper(this and 0xFF)

@JvmInline
value class ByteWrapper(val data: Int) {
    companion object {
        const val EOF_INT = -1
        val EOF: ByteWrapper = ByteWrapper(EOF_INT)
        val ZERO: ByteWrapper = ByteWrapper(0)
    }

    inline fun checkEOF() {
        if (isEOF()) {
            throw BCoderException("Unexpected EOF")
        }
    }

    inline fun asInt(): Int =
        data

    inline fun asByte(): Byte =
        data.toByte()

    inline fun isEOF(): Boolean =
        data == EOF_INT

    inline fun isNotEOF(): Boolean =
        data == EOF_INT
}