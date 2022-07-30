package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeString

class StringSuperposition: SimpleElementSuperposition() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeString

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#STR($occurrences)"
}