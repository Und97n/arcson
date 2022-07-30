package org.philips.arcson.test_utils

import org.philips.arcson.bcoder.DataSink
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.stream.Collectors
import java.util.stream.IntStream

class OutputFixture: DataSink {
    private var buffer = ByteArrayOutputStream()

    override fun write(b: Int) {
        buffer.write(b)
    }

    fun toBytes(): ByteArray =
        buffer.toByteArray()

    fun toByteBuffer(): ByteBuffer =
        ByteBuffer.wrap(toBytes())

    fun toHexString(): String =
        toByteBuffer().let { buffer ->
            IntStream
                .generate { buffer.get().toInt() and 0xFF }
                .limit(buffer.remaining().toLong())
        }
            .mapToObj { String.format("%1\$02X", it) }
            .collect(Collectors.joining(" "))
}