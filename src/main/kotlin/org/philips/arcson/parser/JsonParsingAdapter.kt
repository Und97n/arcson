package org.philips.arcson.parser

import org.philips.arcson.FieldName
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

interface JsonParsingAdapter<Node, ComplexNode: Node, SimpleNode: Node, Context> {
    fun onRoot(context: Context): ComplexNode

    fun onNextSimpleEntry(
        context: Context,
        parent: ComplexNode,
        type: ArcsonValueTypeSimple,
        fieldName: FieldName? = null,
        value: Any?
    ): SimpleNode

    fun onNextComplexEntry(
        context: Context,
        parent: ComplexNode,
        type: ArcsonValueTypeComplex,
        fieldName: FieldName? = null
    ): ComplexNode
}