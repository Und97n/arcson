package org.philips.arcson.bcoder.common

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import java.nio.charset.StandardCharsets

object ShittyString {
    fun encodeShitty(sink: DataSink, value: String) {
        sink.write(value.toByteArray(StandardCharsets.UTF_8))
        sink.write(ByteWrapper.ZERO) // Safe because of UTF-8
    }

    fun decodeShitty(source: DataSource): String {
        val data = source.readWhile(ByteWrapper.ZERO)
        return String(data, StandardCharsets.UTF_8)
    }
}