package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonValueType
import org.philips.arcson.type.ArcsonValueTypeNumber

class NumberSuperposition: SimpleElementSuperposition() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonValueType
        get() = ArcsonValueTypeNumber

    override fun toNiceString(indent: StringIndentation): String = "$indent#NUM($occurrences)"
}