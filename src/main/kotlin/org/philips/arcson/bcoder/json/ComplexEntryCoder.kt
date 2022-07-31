package org.philips.arcson.bcoder.json

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.Coder
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

interface ComplexEntryCoder: Coder {
    fun encodeSimpleMemberHeader(sink: DataSink, type: ArcsonTypeSimple, fieldName: FieldName?): SimpleEntryCoder
    fun encodeComplexMemberHeader(sink: DataSink, type: ArcsonTypeComplex, fieldName: FieldName?): ComplexEntryCoder
    fun encodeFooter(sink: DataSink)

    fun<C> decode(source: DataSource, attachment: C, callbacks: DecodingCallbacks<C>)

    interface DecodingCallbacks<C> {
        fun onNextSimpleEntry(coder: SimpleEntryCoder, fieldName: FieldName?, attachment: C)
        fun onNextComplexEntry(coder: ComplexEntryCoder, fieldName: FieldName?, attachment: C)
    }
}