package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.superposition.*
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeArray
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeSimple

class ArraySuperposition() : ComplexElementSuperposition() {
    override fun nextSimpleEncounter(type: ArcsonTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition =
        collection
            .getOrMake(type, type::createSuperpositionS)
            .let { it as SimpleElementSuperposition }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonTypeComplex, name: FieldName?): ComplexElementSuperposition =
        collection
            .getOrMake(type, type::createSuperpositionC)
            .let { it as ComplexElementSuperposition }

    override val type: ArcsonType
        get() = ArcsonTypeArray

    private val collection: JsonField = JsonField()

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#ARRAY($occurrences) [${collection.toNiceString(indent.next())}$indent]"
}