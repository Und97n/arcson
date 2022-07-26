package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.schema.blueprint.JsonBlueprint

abstract class JsonSimpleInstanceBlueprint: JsonBlueprint() {
    abstract fun nextValue(value: Any?)
}