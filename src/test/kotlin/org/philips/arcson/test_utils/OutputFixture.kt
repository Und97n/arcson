package org.philips.arcson.test_utils

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.common.ByteWrapper
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.stream.Collectors
import java.util.stream.IntStream

class OutputFixture: DataSink {
    private var buffer = ByteArrayOutputStream()

    override fun write(b: ByteWrapper) {
        b.checkEOF()
        buffer.write(b.asInt())
    }

    fun toBytes(): ByteArray =
        buffer.toByteArray()

    fun toByteBuffer(): ByteBuffer =
        ByteBuffer.wrap(toBytes())

    fun toHexString(): String =
        Utils.bytesToHex(toByteBuffer())

}