package org.philips.arcson.test_utils

import org.philips.arcson.bcoder.DataSource
import java.util.HexFormat

class InputFixture(_data: ByteArray): DataSource {
    private val data: ByteArray = _data
    private var ptr = 0

    companion object {
        fun fromHexString(hex: String): InputFixture =
            InputFixture(HexFormat.of().parseHex(hex.replace("\\s+".toRegex(),"")))
    }

    override fun read(): Int =
        if (ptr < data.size) {
            data[ptr++].toInt() and 0xff
        } else {
            -1
        }
}