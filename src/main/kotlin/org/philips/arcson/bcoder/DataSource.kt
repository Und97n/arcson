package org.philips.arcson.bcoder

import org.philips.arcson.bcoder.common.ByteWrapper
import java.io.ByteArrayOutputStream

interface DataSource {
    companion object {
        const val EOF: Int = -1
    }

    /**
     * Read a byte, return ByteWrapper.EOF if end of data
      */
    fun read(): ByteWrapper

    /**
     * Read bytes while some particular byte not encountered
     * If EOF = return NULL
     */
    fun readWhile(delimiterByte: ByteWrapper): ByteArray {
        val buf = ByteArrayOutputStream()

        var b = read()

        while (b != delimiterByte) {
            b.checkEOF()

            buf.write(b.asInt())

            b = read()
        }

        return buf.toByteArray()
    }

    fun debugCurrentState(msg: String) {
        throw NotImplementedError()
    }
}