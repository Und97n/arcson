package org.philips.arcson.text

import com.jsoniter.JsonIterator
import com.jsoniter.ValueType
import org.philips.arcson.FieldName
import org.philips.arcson.JsonHandler
import org.philips.arcson.type.*
import java.io.InputStream

class JsonParser<Node, ComplexNode: Node, SimpleNode: Node> private constructor(
    private val iterator: JsonIterator,
    private val adapter: JsonHandler<Node, ComplexNode, SimpleNode>
) {

    constructor(_inputStream: InputStream, _adapter: JsonHandler<Node, ComplexNode, SimpleNode>):
            this(JsonIterator.parse(_inputStream, 0), _adapter)

    constructor(_inputStr: String, _adapter: JsonHandler<Node, ComplexNode, SimpleNode>):
            this(JsonIterator.parse(_inputStr), _adapter)

    private fun parseEntryAux(node: ComplexNode, fName: FieldName?): Node =
        when (iterator.whatIsNext()) {
            ValueType.OBJECT ->
                parseObject(adapter.onNextComplexEntry(node, ArcsonTypeObject, fName))
            ValueType.ARRAY ->
                parseArray(adapter.onNextComplexEntry(node, ArcsonTypeArray, fName))
            ValueType.STRING ->
                adapter.onNextSimpleEntry(node, ArcsonTypeString, fName, iterator.readString())
            ValueType.NUMBER ->
                adapter.onNextSimpleEntry(node, ArcsonTypeNumber, fName, iterator.readNumberAsString())
            ValueType.NULL -> TODO()
            ValueType.BOOLEAN -> TODO()
            ValueType.INVALID -> TODO()
            else -> null!!
        }

    private fun parseArray(currentNode: ComplexNode): Node {
        iterator.readArrayCB({ _, _ ->
            parseEntryAux(currentNode, null)
            true
        }, null)

        adapter.onComplexEntryEnd(currentNode)

        return currentNode
    }

    private fun parseObject(currentNode: ComplexNode): Node {
        iterator.readObjectCB({ _, fieldStr, _ ->
            if (fieldStr == null) {
                false
            } else {
                parseEntryAux(currentNode, FieldName(fieldStr))
                true
            }
        }, null)

        adapter.onComplexEntryEnd(currentNode)

        return currentNode
    }

    private fun parseRoot(rootNode: ComplexNode): Node {
        parseEntryAux(rootNode, null)

        adapter.onComplexEntryEnd(rootNode)

        return rootNode
    }

    fun parse() {
        parseRoot(adapter.onRoot())
    }
}