package org.philips.arcson.schema.superposition.models

import org.philips.arcson.type.ArcsonType
import org.philips.arcson.type.ArcsonTypeString
import org.philips.arcson.utils.StringIndentation

class StringSuperposition: SimpleElementSuperposition() {
    override fun nextValue(value: Any?) {

    }

    override val type: ArcsonType
        get() = ArcsonTypeString

    override fun toNiceString(indent: StringIndentation): String =
        "$indent#STR($occurrences)"
}