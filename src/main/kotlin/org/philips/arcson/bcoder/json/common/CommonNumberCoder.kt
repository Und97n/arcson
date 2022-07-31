package org.philips.arcson.bcoder.json.common

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.ShittyNumber
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeNumber

object CommonNumberCoder: SimpleEntryCoder {
    override fun encode(sink: DataSink, value: Any?) =
        ShittyNumber.encodeShitty(sink, (value as String).toInt())

    override fun decode(source: DataSource): Any? =
        ShittyNumber.decodeShitty(source).toString()

    override val targetType: ArcsonType
        get() = ArcsonTypeNumber
}