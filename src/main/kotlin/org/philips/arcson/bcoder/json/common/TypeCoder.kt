package org.philips.arcson.bcoder.json.common

import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.ShittyNumber
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.TypeId
import org.philips.arcson.type.asTypeId

object TypeCoder {
    fun encodeId(sink: DataSink, typeId: TypeId) =
        ShittyNumber.encodeShitty(sink, typeId.asIntValue())

    fun decodeId(source: DataSource): TypeId =
        ShittyNumber.decodeShitty(source).asTypeId()

    fun encode(sink: DataSink, type: ArcsonType) =
        encodeId(sink, type.id)

    fun decode(source: DataSource): ArcsonType =
        decodeId(source).getType()
}