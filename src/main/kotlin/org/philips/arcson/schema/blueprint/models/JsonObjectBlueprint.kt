package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.schema.blueprint.*
import org.philips.arcson.schema.getOrMake
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeObject
import java.util.stream.Collectors

class JsonObjectBlueprint() : JsonComplexBlueprint() {
    override val type: ArcsonValueType
        get() = ArcsonValueTypeObject

    private val collection: MutableMap<FieldName, JsonField> = HashMap()

    override fun nextEncounter(name: FieldName, type: ArcsonValueType): JsonBlueprint =
        collection
            .getOrMake(name, ::JsonField)
            .getOrMake(type, type::createBlueprint)
            .let {
                it.incrementOccurrences()
                it
            }

    override fun toNiceString(indent: StringIndentation): String {
        val nextIndent = indent.next()

        val data = collection.entries.stream()
            .map {  "$nextIndent${it.key} -> ${it.value.toNiceString(nextIndent)}" }
            .collect(Collectors.joining(",\n"))

        return "$indent#OBJ[$occurrences] {\n$data\n$indent}"
    }
}