package org.philips.arcson.schema.blueprint

abstract class JsonComplexObjectBlueprint: JsonBlueprint() {
    abstract fun nextEncounter(name: FieldName, type: ObjType<*>): JsonBlueprint
}