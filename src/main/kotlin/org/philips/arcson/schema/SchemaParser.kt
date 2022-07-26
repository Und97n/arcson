package org.philips.arcson.schema

import org.philips.arcson.FieldName
import org.philips.arcson.parser.JsonParsingAdapter
import org.philips.arcson.schema.blueprint.JsonBlueprint
import org.philips.arcson.schema.blueprint.models.JsonComplexInstanceBlueprint
import org.philips.arcson.schema.blueprint.models.JsonSimpleInstanceBlueprint
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

class SchemaParser: JsonParsingAdapter<JsonBlueprint, JsonComplexInstanceBlueprint, JsonSimpleInstanceBlueprint, Unit> {
    override fun onNextSimpleEntry(
        context: Unit,
        parent: JsonComplexInstanceBlueprint,
        type: ArcsonValueTypeSimple,
        fieldName: FieldName?,
        value: Any?
    ): JsonSimpleInstanceBlueprint =
        parent.nextSimpleEncounter(type, fieldName, value)
            .let {
                it.incrementOccurrences()
                it
            }

    override fun onNextComplexEntry(
        context: Unit,
        parent: JsonComplexInstanceBlueprint,
        type: ArcsonValueTypeComplex,
        fieldName: FieldName?
    ): JsonComplexInstanceBlueprint =
        parent.nextComplexEncounter(type, fieldName)
            .let {
                it.incrementOccurrences()
                it
            }

}

