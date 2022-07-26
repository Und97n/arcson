package org.philips.arcson.schema.blueprint.models

import org.philips.arcson.schema.blueprint.StringIndentation
import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeNumber

class JsonNumberBlueprint: JsonSimpleInstanceBlueprint() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeNumber

    override fun toNiceString(indent: StringIndentation): String = "$indent#NUM($occurrences)"
}