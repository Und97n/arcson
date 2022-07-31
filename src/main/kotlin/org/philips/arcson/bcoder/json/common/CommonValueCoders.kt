package org.philips.arcson.bcoder.json.common

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.bcoder.common.ShittyNumber
import org.philips.arcson.bcoder.common.ShittyString
import org.philips.arcson.bcoder.utils.UnsupportedTypeException
import org.philips.arcson.type.*

object CommonValueCoders {
    object CommonNumberCoder: SimpleEntryCoder {
        override fun encode(sink: DataSink, value: Any?) =
            ShittyNumber.encodeShitty(sink, (value as String).toInt())

        override fun decode(source: DataSource): Any? =
            ShittyNumber.decodeShitty(source).toString()

        override val targetType: ArcsonType
            get() = ArcsonTypeNumber
    }

    object CommonStringCoder: SimpleEntryCoder {
        override fun encode(sink: DataSink, value: Any?) =
            ShittyString.encodeShitty(sink, (value as String))

        override fun decode(source: DataSource): Any? =
            ShittyString.decodeShitty(source)

        override val targetType: ArcsonType
            get() = ArcsonTypeString
    }

    fun chooseCoder(type: ArcsonTypeSimple): SimpleEntryCoder =
        when (type) {
            is ArcsonTypeNumber -> CommonNumberCoder
            is ArcsonTypeString -> CommonStringCoder
            else -> throw UnsupportedTypeException(this.toString(), type)
        }
}