package org.philips.arcson.parser

import com.jsoniter.JsonIterator
import com.jsoniter.ValueType
import org.philips.arcson.FieldName
import org.philips.arcson.type.*
import java.io.InputStream

class JsonParser<Node, ComplexNode: Node, SimpleNode: Node, Context> private constructor(
    private val iterator: JsonIterator,
    private val adapter: JsonParsingAdapter<Node, ComplexNode, SimpleNode, Context>
) {

    constructor(_inputStream: InputStream, _adapter: JsonParsingAdapter<Node, ComplexNode, SimpleNode, Context>):
            this(JsonIterator.parse(_inputStream, 0), _adapter)

    constructor(_inputStr: String, _adapter: JsonParsingAdapter<Node, ComplexNode, SimpleNode, Context>):
            this(JsonIterator.parse(_inputStr), _adapter)

    private fun parseEntryAux(ctx: Context, node: ComplexNode, fName: FieldName?): Node =
        when (iterator.whatIsNext()) {
            ValueType.OBJECT ->
                parseObject(ctx,
                    adapter.onNextComplexEntry(ctx, node, ArcsonValueTypeObject, fName))
            ValueType.ARRAY ->
                parseArray(ctx,
                    adapter.onNextComplexEntry(ctx, node, ArcsonValueTypeArray, fName))
            ValueType.STRING ->
                adapter.onNextSimpleEntry(ctx, node, ArcsonValueTypeString, fName, iterator.readString())
            ValueType.NUMBER ->
                adapter.onNextSimpleEntry(ctx, node, ArcsonValueTypeNumber, fName, iterator.readNumberAsString())
            ValueType.NULL -> TODO()
            ValueType.BOOLEAN -> TODO()
            ValueType.INVALID -> TODO()
            else -> null!!
        }

    private fun parseArray(context: Context, currentNode: ComplexNode): Node {
        iterator.readArrayCB({ _, _ ->
            parseEntryAux(context, currentNode, null)
            true
        }, null)

        return currentNode
    }

    private fun parseObject(context: Context, currentNode: ComplexNode): Node {
        iterator.readObjectCB({ _, fieldStr, _ ->
            if (fieldStr == null) {
                false
            } else {
                parseEntryAux(context, currentNode, FieldName(fieldStr))
                true
            }
        }, null)

        return currentNode
    }

    fun parse(context: Context) {
        parseEntryAux(context, adapter.onRoot(context), null)
    }
}