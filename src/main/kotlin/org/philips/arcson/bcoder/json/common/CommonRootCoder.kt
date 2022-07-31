package org.philips.arcson.bcoder.json.common

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.common.wrapToByteWrapper
import org.philips.arcson.bcoder.json.ComplexEntryCoder
import org.philips.arcson.bcoder.json.SimpleEntryCoder
import org.philips.arcson.bcoder.utils.EncoderException
import org.philips.arcson.bcoder.utils.UnsupportedTypeException
import org.philips.arcson.type.ArcsonSpecialTypeNONE
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

object CommonRootCoder: ComplexEntryCoder {
    override fun encodeSimpleMemberHeader(sink: DataSink, type: ArcsonTypeSimple, fieldName: FieldName?): SimpleEntryCoder {
        if (fieldName != null) {
            throw EncoderException("Unexpected FieldName at $this", sink)
        }

        TypeCoder.encode(sink, type)
        return CommonValueCoders.chooseCoder(type)
    }

    override fun encodeComplexMemberHeader(sink: DataSink, type: ArcsonTypeComplex, fieldName: FieldName?): ComplexEntryCoder {
        if (fieldName != null) {
            throw EncoderException("Unexpected FieldName at $this", sink)
        }

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
            when (tp) {
                is ArcsonTypeSimple -> callbacks.onNextSimpleEntry(CommonValueCoders.chooseCoder(tp), null, attachment)
                is ArcsonTypeComplex -> callbacks.onNextComplexEntry(CommonObjectCoder, null, attachment)
                else -> throw UnsupportedTypeException(this.toString(), tp)
            }

            tp = TypeCoder.decode(source)
        }
    }

    override val targetType: ArcsonType
        get() = ArcsonSpecialTypeNONE

    override fun toString(): String {
        return "CommonRootCoder"
    }
}