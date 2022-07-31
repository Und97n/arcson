package org.philips.arcson.bcoder.json.common

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.ShittyString
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeString

object CommonStringCoder: SimpleEntryCoder {
    override fun encode(sink: DataSink, value: Any?) =
        ShittyString.encodeShitty(sink, (value as String))

    override fun decode(source: DataSource): Any? =
        ShittyString.decodeShitty(source)

    override val targetType: ArcsonType
        get() = ArcsonTypeString
}