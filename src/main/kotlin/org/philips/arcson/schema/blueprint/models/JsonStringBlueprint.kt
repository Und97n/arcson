package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.schema.blueprint.StringIndentation
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeString

class JsonStringBlueprint: JsonSimpleInstanceBlueprint() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeString

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#STR($occurrences)"
}