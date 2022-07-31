package org.philips.arcson.bcoder.json.common

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.ShittyString

object FieldNameCoder {
    fun encode(sink: DataSink, value: FieldName) {
        ShittyString.encodeShitty(sink, value.value)
    }

    fun decode(source: DataSource): FieldName =
        FieldName(ShittyString.decodeShitty(source))
}