package org.philips.arcson.bcoder

import org.philips.arcson.bcoder.common.ByteWrapper
import org.philips.arcson.bcoder.common.wrapToByteWrapper

interface DataSink {
    fun write(byte: ByteWrapper)

    fun write(data: ByteArray) {
        for (byte in data) {
            write(byte.toInt().wrapToByteWrapper())
        }
    }

}