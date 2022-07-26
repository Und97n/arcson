package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.blueprint.*
import org.philips.arcson.schema.getOrMake
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeObject
import org.philips.arcson.type.ArcsonValueTypeSimple
import java.util.stream.Collectors

class JsonObjectBlueprint() : JsonComplexInstanceBlueprint() {
    override val type: ArcsonValueType
        get() = ArcsonValueTypeObject

    private val collection: MutableMap<FieldName, JsonField> = HashMap()

    override fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): JsonSimpleInstanceBlueprint =
        collection
            .getOrMake(name!!, ::JsonField)
            .getOrMake(type, type::createBlueprintS)
            .let { it as JsonSimpleInstanceBlueprint }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): JsonComplexInstanceBlueprint =
        collection
            .getOrMake(name!!, ::JsonField)
            .getOrMake(type, type::createBlueprintC)
            .let { it as JsonComplexInstanceBlueprint }

    override fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        val data = collection.entries.stream()
            .map {  "$nextIndent${it.key} -> ${it.value.toNiceString(nextIndent)}" }
            .collect(Collectors.joining(","))

        return "$indent#OBJ[$occurrences] {$data$indent}"
    }
}