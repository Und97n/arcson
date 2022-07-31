package org.philips.arcson.bcoder.json

import org.philips.arcson.FieldName
import org.philips.arcson.bcoder.DataSink
import org.philips.arcson.bcoder.json.common.CommonRootCoder
import org.philips.arcson.JsonHandler
import org.philips.arcson.bcoder.Coder
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

class JsonEncodingAdapter(_sink: DataSink): JsonHandler<Coder, ComplexEntryCoder, SimpleEntryCoder> {
    private val sink: DataSink = _sink

    override fun onRoot(): ComplexEntryCoder =
        CommonRootCoder

    override fun onNextSimpleEntry(
        parent: ComplexEntryCoder,
        type: ArcsonTypeSimple,
        fieldName: FieldName?,
        value: Any?
    ): SimpleEntryCoder =
        parent.encodeSimpleMemberHeader(sink, type, fieldName).let {
            it.encode(sink, value)
            it
        }

    override fun onNextComplexEntry(
        parent: ComplexEntryCoder,
        type: ArcsonTypeComplex,
        fieldName: FieldName?
    ): ComplexEntryCoder =
        parent.encodeComplexMemberHeader(sink, type, fieldName)

    override fun onComplexEntryEnd(node: ComplexEntryCoder) {
        node.encodeFooter(sink)
    }
}