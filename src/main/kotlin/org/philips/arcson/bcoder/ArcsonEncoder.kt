package org.philips.arcson.bcoder

import java.io.OutputStream

interface ArcsonEncoder {
    fun encode(stream: OutputStream)
}