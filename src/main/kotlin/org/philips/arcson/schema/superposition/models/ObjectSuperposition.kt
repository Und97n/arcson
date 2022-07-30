package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.superposition.*
import org.philips.arcson.schema.getOrMake
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeObject
import org.philips.arcson.type.ArcsonValueTypeSimple
import java.util.stream.Collectors

class ObjectSuperposition() : ComplexElementSuperposition() {
    override val type: ArcsonValueType
        get() = ArcsonValueTypeObject

    private val collection: MutableMap<FieldName, JsonField> = HashMap()

    override fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition =
        collection
            .getOrMake(name!!, ::JsonField)
            .getOrMake(type, type::createSuperpositionS)
            .let { it as SimpleElementSuperposition }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): ComplexElementSuperposition =
        collection
            .getOrMake(name!!, ::JsonField)
            .getOrMake(type, type::createSuperpositionC)
            .let { it as ComplexElementSuperposition }

    override fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        val data = collection.entries.stream()
            .map {  "$nextIndent${it.key} -> ${it.value.toNiceString(nextIndent)}" }
            .collect(Collectors.joining(","))

        return "$indent#OBJ[$occurrences] {$data$indent}"
    }
}