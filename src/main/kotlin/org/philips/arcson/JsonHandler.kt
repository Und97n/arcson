package org.philips.arcson

import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

interface JsonHandler<Node, ComplexNode: Node, SimpleNode: Node> {
    fun onRoot(): ComplexNode

    fun onNextSimpleEntry(
        parent: ComplexNode,
        type: ArcsonTypeSimple,
        fieldName: FieldName? = null,
        value: Any?
    ): SimpleNode

    fun onNextComplexEntry(
        parent: ComplexNode,
        type: ArcsonTypeComplex,
        fieldName: FieldName? = null
    ): ComplexNode

    fun onComplexEntryEnd(
        node: ComplexNode
    ) {}
}