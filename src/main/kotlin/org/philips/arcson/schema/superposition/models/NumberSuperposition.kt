package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeNumber

class NumberSuperposition: SimpleElementSuperposition() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonType
        get() = ArcsonTypeNumber

    override fun toNiceString(indent: StringIndentation): String = "$indent#NUM($occurrences)"
}