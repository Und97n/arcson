package org.philips.arcson.bcoder.common

import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.utils.DecoderException

object ShittyNumber {
    fun encodeShitty(sink: DataSink, value: Int) {
        var accumulator = value

        do {
            var nextByte = accumulator and 0x7F
            accumulator = accumulator ushr 7

            if (accumulator != 0) {
                nextByte = nextByte or 0x80
            }

            sink.write(nextByte.wrapToByteWrapper())
        } while (accumulator != 0)
    }

    fun decodeShitty(source: DataSource): Int {
        var accumulator = 0

        var data: ByteWrapper
        var shift = 0

        do {
            data = source.read()
            if (data.isEOF()) {
                break; // EOF
            }

            if (shift >= 32) throw DecoderException("Too many number bytes", source)

            accumulator = accumulator or ((data.asInt() and 0x7F) shl shift)
            shift += 7
        } while ((data.asInt() and 0x7F) != 0)

        return accumulator
    }
}