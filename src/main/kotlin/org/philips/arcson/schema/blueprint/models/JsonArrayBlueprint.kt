package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.blueprint.*
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeArray
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

class JsonArrayBlueprint() : JsonComplexInstanceBlueprint() {
    override fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): JsonSimpleInstanceBlueprint =
        collection
            .getOrMake(type, type::createBlueprintS)
            .let { it as JsonSimpleInstanceBlueprint }
            .let {
                it.nextValue(value)
                it
            }

    override fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): JsonComplexInstanceBlueprint =
        collection
            .getOrMake(type, type::createBlueprintC)
            .let { it as JsonComplexInstanceBlueprint }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeArray

    private val collection: JsonField = JsonField()

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#ARRAY($occurrences) [${collection.toNiceString(indent.next())}$indent]"
}