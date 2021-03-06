package org.philips.arcson.bcoder.json.common

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.json.ComplexEntryCoder
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.bcoder.json.common.CommonCoders.chooseComplexCoder
import org.philips.arcson.bcoder.json.common.CommonCoders.chooseSimpleCoder
import org.philips.arcson.bcoder.utils.UnsupportedTypeException
import org.philips.arcson.type.*

object CommonObjectCoder: ComplexEntryCoder {
    override fun encodeSimpleMemberHeader(sink: DataSink, type: ArcsonTypeSimple, fieldName: FieldName?): SimpleEntryCoder {
        TypeCoder.encode(sink, type)
        FieldNameCoder.encode(sink, fieldName!!)
        return chooseSimpleCoder(type)
    }

    override fun encodeComplexMemberHeader(sink: DataSink, type: ArcsonTypeComplex, fieldName: FieldName?): ComplexEntryCoder {
        TypeCoder.encode(sink, type)
        FieldNameCoder.encode(sink, fieldName!!)
        return chooseComplexCoder(type)
    }

    override fun encodeFooter(sink: DataSink) {
        TypeCoder.encode(sink, ArcsonSpecialTypeNONE)
    }

    override fun <C> decode(
        source: DataSource,
        attachment: C,
        callbacks: ComplexEntryCoder.DecodingCallbacks<C>
    ) {
        var tp = TypeCoder.decode(source)

        while (tp !is ArcsonSpecialTypeNONE) {
            val fieldName = FieldNameCoder.decode(source)

            when (tp) {
                is ArcsonTypeSimple -> callbacks.onNextSimpleEntry(chooseSimpleCoder(tp), fieldName, attachment)
                is ArcsonTypeComplex -> callbacks.onNextComplexEntry(chooseComplexCoder(tp), fieldName, attachment)
                else -> throw UnsupportedTypeException(this.toString(), tp)
            }

            tp = TypeCoder.decode(source)
        }
    }

    override val targetType: ArcsonType
        get() = ArcsonTypeObject

    override fun toString(): String {
        return "CommonObjectCoder"
    }
}