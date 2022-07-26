package org.philips.arcson.bcoder.utils

import java.io.BufferedInputStream
import java.io.OutputStream

object ShittyNumber {
    fun encodeShitty(value: Int, stream: OutputStream) {
        var accumulator = value

        do {
            var nextByte = accumulator and 0x7F
            accumulator = accumulator ushr 7

            if (accumulator != 0) {
                nextByte = nextByte or 0x80
            }

            stream.write(nextByte)
        } while (accumulator != 0)
    }

    fun decodeShitty(stream: BufferedInputStream): Int {
        var accumulator = 0

        var data: Int
        var shift = 0

        do {
            data = stream.read()
            if (data == -1) {
                break; // EOF
            }

            if (shift >= 32) throw DecoderException("Too many number bytes", stream)

            accumulator = accumulator or ((data and 0x7F) shl shift)
            shift += 7
        } while ((data and 0x7F) != 0)

        return accumulator
    }
}