package org.philips.arcson.schema.blueprint

import org.philips.arcson.common.type.JsonValueType

abstract class JsonComplexBlueprint: JsonBlueprint() {
    abstract fun nextEncounter(name: FieldName, type: JsonValueType): JsonBlueprint
}