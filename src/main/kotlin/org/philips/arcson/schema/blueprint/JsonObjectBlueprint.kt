package org.philips.arcson.schema.blueprint

import org.philips.arcson.schema.getOrMake
import java.util.stream.Collectors

class JsonObjectBlueprint() : JsonComplexObjectBlueprint() {
    companion object {
        val TYPE = ObjType(JsonBlueprint::class.java, ::JsonObjectBlueprint)
    }

    override val type: ObjType<*>
        get() = TYPE

    private val collection: MutableMap<FieldName, Field> = HashMap()

    override fun nextEncounter(name: FieldName, type: ObjType<*>): JsonBlueprint =
        collection
            .getOrMake(name, ::Field)
            .getOrMake(type, type::newInstance)
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