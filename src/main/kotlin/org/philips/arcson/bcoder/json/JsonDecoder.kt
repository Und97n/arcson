package org.philips.arcson.bcoder.json

import org.philips.arcson.FieldName
import org.philips.arcson.JsonHandler
import org.philips.arcson.bcoder.DataSource
import org.philips.arcson.bcoder.json.common.CommonRootCoder
import org.philips.arcson.type.*

class JsonDecoder<Node, ComplexNode: Node, SimpleNode: Node>(
    private val source: DataSource,
    private val adapter: JsonHandler<Node, ComplexNode, SimpleNode>
): ComplexEntryCoder.DecodingCallbacks<ComplexNode> {

    override fun onNextSimpleEntry(coder: SimpleEntryCoder, fieldName: FieldName?, attachment: ComplexNode) {
        adapter.onNextSimpleEntry(attachment,
            coder.targetType as ArcsonTypeSimple,
            fieldName,
            coder.decode(source))
    }

    override fun onNextComplexEntry(coder: ComplexEntryCoder, fieldName: FieldName?, attachment: ComplexNode) {
        val node = adapter.onNextComplexEntry(attachment,
            coder.targetType as ArcsonTypeComplex,
            fieldName)

        coder.decode(source, node, this)

        adapter.onComplexEntryEnd(attachment)
    }

    fun decode() {
        CommonRootCoder.decode(source, adapter.onRoot(), this)
    }
}