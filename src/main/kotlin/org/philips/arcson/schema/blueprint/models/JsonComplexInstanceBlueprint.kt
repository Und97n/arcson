package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.FieldName
import org.philips.arcson.schema.blueprint.JsonBlueprint
import org.philips.arcson.type.ArcsonValueTypeComplex
import org.philips.arcson.type.ArcsonValueTypeSimple

abstract class JsonComplexInstanceBlueprint: JsonBlueprint() {
    abstract fun nextSimpleEncounter(type: ArcsonValueTypeSimple, name: FieldName?, value: Any?): JsonSimpleInstanceBlueprint
    abstract fun nextComplexEncounter(type: ArcsonValueTypeComplex, name: FieldName?): JsonComplexInstanceBlueprint
}