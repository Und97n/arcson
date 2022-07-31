package org.philips.arcson.bcoder.json

import org.philips.arcson.bcoder.Coder
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource

interface SimpleEntryCoder: Coder {
    fun encode(sink: DataSink, value: Any?)
    fun decode(source: DataSource): Any?
}