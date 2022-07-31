package org.philips.arcson.schema.superposition.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.superposition.*
import org.philips.arcson.schema.getOrMake
import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeComplex
import org.philips.arcson.type.ArcsonTypeObject
import org.philips.arcson.type.ArcsonTypeSimple
import org.philips.arcson.utils.StringIndentation
import java.util.stream.Collectors

class ObjectSuperposition() : ComplexElementSuperposition() {
    override val type: ArcsonType
        get() = ArcsonTypeObject

    private val collection: MutableMap<FieldName, JsonField> = HashMap()

    override fun nextSimpleEncounter(type: ArcsonTypeSimple, name: FieldName?, value: Any?): SimpleElementSuperposition =
        collection
            .getOrMake(name!!, ::JsonField)
            .getOrMake(type, type::createSuperpositionS)
            .let { it as SimpleElementSuperposition }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonTypeComplex, name: FieldName?): ComplexElementSuperposition =
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