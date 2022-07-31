package org.philips.arcson.bcoder.json.common

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.json.ComplexEntryCoder
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.wrapToByteWrapper
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.bcoder.utils.UnsupportedTypeException
import org.philips.arcson.type.*
import java.util.function.BiConsumer

object CommonObjectCoder: ComplexEntryCoder {
    override fun encodeSimpleMemberHeader(sink: DataSink, type: ArcsonTypeSimple, fieldName: FieldName?): SimpleEntryCoder {
        FieldNameCoder.encode(sink, fieldName!!)
        TypeCoder.encode(sink, type)
        return CommonValueCoders.chooseCoder(type)
    }

    override fun encodeComplexMemberHeader(sink: DataSink, type: ArcsonTypeComplex, fieldName: FieldName?): ComplexEntryCoder {
        FieldNameCoder.encode(sink, fieldName!!)
        TypeCoder.encode(sink, type)
        return CommonObjectCoder
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
                is ArcsonTypeSimple -> callbacks.onNextSimpleEntry(CommonValueCoders.chooseCoder(tp), fieldName, attachment)
                is ArcsonTypeComplex -> callbacks.onNextComplexEntry(CommonObjectCoder, fieldName, attachment)
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