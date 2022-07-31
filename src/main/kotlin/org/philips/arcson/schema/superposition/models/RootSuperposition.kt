package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.superposition.JsonField
import org.philips.arcson.schema.superposition.createSuperpositionC
import org.philips.arcson.schema.superposition.createSuperpositionS
import org.philips.arcson.type.*
import org.philips.arcson.utils.StringIndentation

class RootSuperposition : ComplexElementSuperposition() {
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
        get() = ArcsonSpecialTypeNONE

    private val collection: JsonField = JsonField()

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#ROOT($occurrences) [${collection.toNiceString(indent.next())}$indent]"
}