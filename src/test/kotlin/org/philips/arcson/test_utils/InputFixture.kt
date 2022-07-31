package org.philips.arcson.test_utils

import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.ByteWrapper
import org.philips.arcson.bcoder.common.wrapToByteWrapper
import java.util.*

class InputFixture(_data: ByteArray): DataSource {
    private val data: ByteArray = _data
    private var ptr = 0

    companion object {
        fun fromHexString(hex: String): InputFixture =
            InputFixture(HexFormat.of().parseHex(hex.replace("\\s+".toRegex(),"")))
    }

    override fun read(): ByteWrapper =
        if (ptr < data.size) {
            (data[ptr++].toInt() and 0xff).wrapToByteWrapper()
        } else {
            ByteWrapper.EOF
        }

    override fun debugCurrentState(msg: String) {
        val before = Utils.bytesToHex(data.copyOf(ptr))
        val after = Utils.bytesToHex(data.copyOfRange(ptr, data.size))

        System.out.println("DEBUG at: $msg\n\t$before -> $after\n")
    }
}