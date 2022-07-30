package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.superposition.*
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeArray
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

class ArraySuperposition() : ComplexElementSuperposition() {
    override fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition =
        collection
            .getOrMake(type, type::createSuperpositionS)
            .let { it as SimpleElementSuperposition }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): ComplexElementSuperposition =
        collection
            .getOrMake(type, type::createSuperpositionC)
            .let { it as ComplexElementSuperposition }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeArray

    private val collection: JsonField = JsonField()

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#ARRAY($occurrences) [${collection.toNiceString(indent.next())}$indent]"
}