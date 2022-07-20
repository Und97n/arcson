package org.philips.arcson.arcson

import java.io.OutputStream

interface ArcsonEncoder {
    fun encode(stream: OutputStream)
}