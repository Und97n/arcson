package org.philips.arcson.test_utils

import java.nio.ByteBuffer
import java.util.stream.Collectors
import java.util.stream.IntStream

object Utils {
    fun bytesToHex(buffer: ByteBuffer, limit: Int = buffer.limit()): String =
        IntStream
            .generate { buffer.get().toInt() and 0xFF }
            .limit(limit.toLong())
            .mapToObj { String.format("%1\$02X", it) }
            .collect(Collectors.joining(" "))

    fun bytesToHex(array: ByteArray, limit: Int = array.size): String =
        bytesToHex(ByteBuffer.wrap(array), limit)
}